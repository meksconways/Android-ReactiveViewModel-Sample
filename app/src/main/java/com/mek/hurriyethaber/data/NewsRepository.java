package com.mek.hurriyethaber.data;

import com.mek.hurriyethaber.articlenews.model.NewsModel;
import com.mek.hurriyethaber.detail.model.NewsDetailModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Singleton
public class NewsRepository {

    private final Provider<ApiRequester> apiRequesterProvider;
    private final List<NewsModel> cachedNews = new ArrayList<>();

    @Inject
    public NewsRepository(Provider<ApiRequester> apiRequesterProvider) {
        this.apiRequesterProvider = apiRequesterProvider;
    }

    public Single<List<NewsModel>> getArticleNews(){

        return Maybe.concat(cachedArticleNews(),apiArticleNews())
                .firstOrError();


    }

//    public Single<NewsModel> getNews(String id){
//
//    }


    private Maybe<NewsDetailModel> apiNewsDetail(String id){
        return apiRequesterProvider.get().getNewsDetail(id)
                .toMaybe();
    }

    public Maybe<NewsModel> cachedNews(String id){

        return Maybe.create(e -> {

            for (NewsModel cachedModel : cachedNews) {
                if (cachedModel.id().equals(id)){
                    e.onSuccess(cachedModel);
                    break;
                }
            }
            e.onComplete();

        });

    }

    public Maybe<List<NewsModel>> cachedArticleNews(){

        return Maybe.create(e -> {

            if (!cachedNews.isEmpty()){
                e.onSuccess(cachedNews);
            }

            e.onComplete();

        });

    }

    private Maybe<List<NewsModel>> apiArticleNews(){

        return apiRequesterProvider.get().getArticles()
                .doOnSuccess(newsModels -> {

                    cachedNews.clear();
                    cachedNews.addAll(newsModels);

                })
                .toMaybe();


    }


}

package com.mek.hurriyethaber.data;

import com.mek.hurriyethaber.articlenews.model.NewsModel;
import com.mek.hurriyethaber.detail.model.NewsDetailModel;
import com.mek.hurriyethaber.util.AppHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ApiRequester {

    private final ApiService apiService;

    @Inject
    ApiRequester(ApiService apiService) {
        this.apiService = apiService;
    }


    public Single<List<NewsModel>> getArticles() {
        return apiService.getArticles(AppHelper.KEY)
                .map(newsModels -> newsModels)
                .subscribeOn(Schedulers.io());

    }

    public Single<NewsDetailModel> getNewsDetail(String news_id){
        return apiService.getNewsDetail(AppHelper.KEY,news_id)
                .subscribeOn(Schedulers.io());
    }


}








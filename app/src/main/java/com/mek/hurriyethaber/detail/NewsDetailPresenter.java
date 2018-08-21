package com.mek.hurriyethaber.detail;

import android.util.Log;

import com.mek.hurriyethaber.data.ApiRequester;
import com.mek.hurriyethaber.data.NewsRepository;
import com.mek.hurriyethaber.detail.model.NewsDetailModel;
import com.mek.hurriyethaber.di.ScreenScope;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.functions.BiConsumer;

@ScreenScope
public class NewsDetailPresenter {



    private final NewsRepository repository;
    private final NewsDetailViewModel viewModel;
    private final ApiRequester requester;

    @Inject
    public NewsDetailPresenter(
            @Named("news_id") String newsID,
            NewsRepository repository,
            NewsDetailViewModel viewModel, ApiRequester requester) {

        try {

            Log.d( "NewsDetailPresenter: ",newsID);

        }catch (Exception e){
            e.printStackTrace();
        }


        this.repository = repository;
        this.viewModel = viewModel;
        this.requester = requester;

        loadDetailPage(newsID);

//        repository.getNewsDetail(newsID)
//                .doOnSuccess(viewModel.processNewsDetail())
//                .doOnError(viewModel.onError())
//                .subscribe();
        // todo

    }

    private void loadDetailPage(String newsID) {

        requester.getNewsDetail(newsID)
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d,t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.detailNewsUpdated(),viewModel.onnError());

    }


}

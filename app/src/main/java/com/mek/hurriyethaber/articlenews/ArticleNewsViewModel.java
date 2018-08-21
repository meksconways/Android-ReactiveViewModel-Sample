package com.mek.hurriyethaber.articlenews;

import android.util.Log;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.mek.hurriyethaber.R;
import com.mek.hurriyethaber.articlenews.model.NewsModel;
import com.mek.hurriyethaber.di.ScreenScope;

import java.util.List;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

@ScreenScope
class ArticleNewsViewModel {

    @Inject
    ArticleNewsViewModel() {

    }


    private final BehaviorRelay<List<NewsModel>> newsRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();




    public Observable<Boolean> getLoadingRelay() {
        return loadingRelay;
    }

    public Observable<Integer> getErrorRelay() {
        return errorRelay;
    }

    public Observable<List<NewsModel>> getNewsRelay() {
        return newsRelay;
    }

    public Consumer<Boolean> loadingUpdated(){

        return loadingRelay;
    }

    public Consumer<Throwable> onError(){
        return throwable -> {
            Log.e( "errorUpdated: ","bir hata oluştu", throwable);
            errorRelay.accept(R.string.api_error);
        };
    }
    public Consumer<List<NewsModel>> newsUpdated(){
        errorRelay.accept(-1);
        return newsRelay;
    }



}

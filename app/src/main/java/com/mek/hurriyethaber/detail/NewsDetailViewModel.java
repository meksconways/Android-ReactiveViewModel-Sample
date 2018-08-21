package com.mek.hurriyethaber.detail;

import android.util.Log;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.mek.hurriyethaber.R;
import com.mek.hurriyethaber.detail.model.NewsDetailModel;
import com.mek.hurriyethaber.di.ScreenScope;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

@ScreenScope
class NewsDetailViewModel {


    @Inject
    NewsDetailViewModel() {
        //newsDetailRelay.accept(NewsDetailState.builder().loading(true).build());
    }

    private final BehaviorRelay<NewsDetailState> newsDetailRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();
    private final BehaviorRelay<NewsDetailModel> detailModelBehaviorRelay = BehaviorRelay.create();


    Observable<NewsDetailModel> getNewsDetailModelObservable(){
        return detailModelBehaviorRelay;
    }

    Observable<Integer> getErrorRelay(){
        return errorRelay;
    }

    Observable<Boolean> getLoadingRelay(){
        return loadingRelay;
    }

    Observable<NewsDetailState> detailStateObservable(){
        return newsDetailRelay;
    }

    public Consumer<Boolean> loadingUpdated(){
        return loadingRelay;
    }

    public Consumer<NewsDetailModel> detailNewsUpdated(){
        errorRelay.accept(-1);
        return detailModelBehaviorRelay;
    }

    public Consumer<Throwable> onnError(){
        return throwable -> {
            Log.e( "errorUpdated: ","bir hata oluştu", throwable);
            errorRelay.accept(R.string.api_error);
        };
    }

    Consumer<NewsDetailModel> processNewsDetail(){
        return detail -> {
            newsDetailRelay.accept(

                    NewsDetailState.builder()
                            .loading(false)
                            .text(detail.text())
                            .title(detail.title())
                            .fileUrl(detail.files().get(0).fileUrl())
                            .build()
            );

        };
    }

    public Consumer<Throwable> onError(){
        return throwable -> {
            Log.e( "errorUpdated: ","bir hata oluştu", throwable);
            newsDetailRelay.accept(
                    NewsDetailState.builder()
                            .errorText(R.string.api_error)
                            .loading(false)
                            .build()
            );
        };
    }


}

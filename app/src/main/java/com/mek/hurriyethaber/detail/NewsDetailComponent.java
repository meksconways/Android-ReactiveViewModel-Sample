package com.mek.hurriyethaber.detail;

import com.mek.hurriyethaber.di.ScreenScope;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface NewsDetailComponent extends AndroidInjector<NewsDetailController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<NewsDetailController>{

        @BindsInstance
        public abstract void bindNewsId(@Named("news_id") String newsID);

        @Override
        public void seedInstance(NewsDetailController instance) {
            bindNewsId(instance.getArgs().getString(NewsDetailController.NEWS_ID));
        }
    }

}

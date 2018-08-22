package com.mek.hurriyethaber.home;

import com.bluelinelabs.conductor.Controller;
import com.mek.hurriyethaber.detail.NewsDetailComponent;
import com.mek.hurriyethaber.detail.NewsDetailController;
import com.mek.hurriyethaber.di.ControllerKey;
import com.mek.hurriyethaber.articlenews.ArticleNewsComponent;
import com.mek.hurriyethaber.articlenews.ArticleNewsController;
import com.mek.hurriyethaber.gallerycontoller.GalleryController;
import com.mek.hurriyethaber.gallerycontoller.GalleryControllerComponent;
import com.mek.hurriyethaber.homecontroller.HomeController;
import com.mek.hurriyethaber.homecontroller.HomeControllerComponent;
import com.mek.hurriyethaber.videocontroller.VideoController;
import com.mek.hurriyethaber.videocontroller.VideoControllerComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module (subcomponents = {
        ArticleNewsComponent.class,
        NewsDetailComponent.class,
        HomeControllerComponent.class,
        VideoControllerComponent.class,
        GalleryControllerComponent.class,

})
public abstract class MainScreenBindingModule {


    @Binds
    @IntoMap
    @ControllerKey(ArticleNewsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindsMainNewsControllerInjector(ArticleNewsComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(NewsDetailController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindsNewsDetailControllerInjector(NewsDetailComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(HomeController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindsHomeControllerInjector(HomeControllerComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(VideoController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindsVideoControllerInjector(VideoControllerComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(GalleryController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindsGalleryControllerInjector(GalleryControllerComponent.Builder builder);

}

package com.mek.hurriyethaber.home;

import com.bluelinelabs.conductor.Controller;
import com.mek.hurriyethaber.di.ControllerKey;
import com.mek.hurriyethaber.articlenews.ArticleNewsComponent;
import com.mek.hurriyethaber.articlenews.ArticleNewsController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module (subcomponents = {
        ArticleNewsComponent.class,

})
public abstract class MainScreenBindingModule {


    @Binds
    @IntoMap
    @ControllerKey(ArticleNewsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindsMainNewsControllerInjector(ArticleNewsComponent.Builder builder);



}

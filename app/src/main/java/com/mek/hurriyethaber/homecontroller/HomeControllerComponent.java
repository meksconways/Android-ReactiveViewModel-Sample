package com.mek.hurriyethaber.homecontroller;

import com.mek.hurriyethaber.di.ScreenScope;
import com.mek.hurriyethaber.ui.TabNavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface HomeControllerComponent extends AndroidInjector<HomeController>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeController>{

        @Override
        public void seedInstance(HomeController instance) {

        }
    }


}

package com.mek.hurriyethaber.home;

import com.mek.hurriyethaber.di.ActivityScope;
import com.mek.hurriyethaber.networking.ServiceModule;
import com.mek.hurriyethaber.ui.NavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class,
})
public interface MainActivityComponent extends AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{

        @Override
        public void seedInstance(MainActivity instance) {

        }
    }

}

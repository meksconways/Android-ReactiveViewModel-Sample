package com.mek.hurriyethaber.ui;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator bindScreenNavigator(DefaultScreenNavigator navigator);




}

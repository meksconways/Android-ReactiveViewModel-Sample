package com.mek.hurriyethaber.ui;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TabNavigationModule {

    @Binds
    abstract TabScreenNav bindTabScreenNavigator(TabScreenNavigator tabScreenNavigator);


}

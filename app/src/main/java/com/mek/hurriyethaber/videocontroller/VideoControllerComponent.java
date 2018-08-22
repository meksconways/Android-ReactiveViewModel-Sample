package com.mek.hurriyethaber.videocontroller;

import com.mek.hurriyethaber.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface VideoControllerComponent extends AndroidInjector<VideoController>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<VideoController>{


        @Override
        public void seedInstance(VideoController instance) {

        }
    }

}

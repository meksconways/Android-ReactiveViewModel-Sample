package com.mek.hurriyethaber.gallerycontoller;

import com.mek.hurriyethaber.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface GalleryControllerComponent extends AndroidInjector<GalleryController>{


    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<GalleryController>{


        @Override
        public void seedInstance(GalleryController instance) {

        }
    }

}

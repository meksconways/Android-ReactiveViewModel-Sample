package com.mek.hurriyethaber.articlenews;

import com.mek.hurriyethaber.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent
public interface ArticleNewsComponent extends AndroidInjector<ArticleNewsController>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ArticleNewsController>{

    }

}

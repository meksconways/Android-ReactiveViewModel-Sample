package com.mek.hurriyethaber.articlenews;

import com.mek.hurriyethaber.articlenews.adapter.ArticleNewsAdapter;
import com.mek.hurriyethaber.articlenews.model.NewsModel;
import com.mek.hurriyethaber.data.ApiRequester;
import com.mek.hurriyethaber.di.ScreenScope;

import javax.inject.Inject;

@ScreenScope
class ArticleNewsPresenter implements ArticleNewsAdapter.ArticleClickListener {


    private final ArticleNewsViewModel viewModel;
    private final ApiRequester requester;

    @Inject
    ArticleNewsPresenter(ArticleNewsViewModel viewModel, ApiRequester requester) {

        this.viewModel = viewModel;
        this.requester = requester;
        loadArticleNews();
    }

    private void loadArticleNews() {
        requester.getArticles()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d,t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.newsUpdated(), viewModel.onError());
    }


    @Override
    public void onClick(NewsModel model) {

    }
}

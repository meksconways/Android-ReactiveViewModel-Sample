package com.mek.hurriyethaber.articlenews;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mek.hurriyethaber.R;
import com.mek.hurriyethaber.articlenews.adapter.ArticleNewsAdapter;
import com.mek.hurriyethaber.base.BaseController;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ArticleNewsController extends BaseController {

    @Inject
    ArticleNewsViewModel viewModel;
    @Inject
    ArticleNewsPresenter presenter;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.txt_error)
    TextView txt_error;
    @BindView(R.id.swipelyt)
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.getLoadingRelay()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loading -> {
                    progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
                    recyclerView.setVisibility(loading ? View.GONE : View.VISIBLE);
                    txt_error.setVisibility(loading ? View.GONE : txt_error.getVisibility());
                }),

                viewModel.getNewsRelay()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(((ArticleNewsAdapter)recyclerView.getAdapter())::setData),

                viewModel.getRefreshRelay()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(refresh -> {


                    if (!refresh){
                        swipeRefreshLayout.setRefreshing(false);
                        recyclerView.scrollToPosition(0);
                    }

                }),

                viewModel.getErrorRelay()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(errorRes -> {

                    if (errorRes == -1){
                        txt_error.setText(null);
                        txt_error.setVisibility(View.GONE);
                    }else {

                        txt_error.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        txt_error.setText(errorRes);

                    }

                })

        };
    }

    @Override
    protected void onViewBound(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new ArticleNewsAdapter(presenter));
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.refreshPage());

    }

    @Override
    protected int layoutRes() {
        return R.layout.layout_articles;
    }



}

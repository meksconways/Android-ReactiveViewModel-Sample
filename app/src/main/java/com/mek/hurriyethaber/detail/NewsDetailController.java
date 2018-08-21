package com.mek.hurriyethaber.detail;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mek.hurriyethaber.R;
import com.mek.hurriyethaber.base.BaseController;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class NewsDetailController extends BaseController{


    static final String NEWS_ID = "news_id";

    @Inject
    NewsDetailPresenter presenter;
    @Inject
    NewsDetailViewModel viewModel;

    @BindView(R.id.sc_scrollview)
    ScrollView scrollView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.news_photo)
    ImageView news_photo;
    @BindView(R.id.txt_title)
    TextView txt_title;
    @BindView(R.id.txt_body)
    TextView txt_body;
    @BindView(R.id.txt_error)
    TextView txt_error;

    public static NewsDetailController newInstance(String newsId) {
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_ID,newsId);
        return new NewsDetailController(bundle);
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{

                viewModel.getLoadingRelay()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
                    scrollView.setVisibility(loading ? View.GONE : View.VISIBLE);
                    txt_error.setVisibility(loading ? View.GONE : txt_error.getVisibility());
                }),

                viewModel.getErrorRelay()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(errorRes -> {

                    if (errorRes == -1){
                        txt_error.setText(null);
                        txt_error.setVisibility(View.GONE);
                    }else {

                        txt_error.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.GONE);
                        txt_error.setText(errorRes);

                    }

                }),

                viewModel.getNewsDetailModelObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detail -> {

                    txt_title.setText(detail.title());
                    //txt_body.setText(detail.text());

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        txt_body.setText(Html.fromHtml(detail.text(),Html.FROM_HTML_MODE_LEGACY));
                    } else {
                        txt_body.setText(Html.fromHtml(detail.text()));
                    }


                    if (detail.files() != null && detail.files().size() != 0){
                        Glide.with(txt_body.getContext())
                                .load(detail.files().get(0).fileUrl())
                                .apply(new RequestOptions().override(600,340))
                                .thumbnail(0.2f)
                                .into(news_photo);
                    }



                })


        };
    }

    public NewsDetailController(Bundle args) {
        super(args);
    }



    @Override
    protected int layoutRes() {
        return R.layout.layout_newsdetail;
    }
}

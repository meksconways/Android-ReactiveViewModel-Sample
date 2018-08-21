package com.mek.hurriyethaber.detail;

import android.os.Bundle;

import com.mek.hurriyethaber.R;
import com.mek.hurriyethaber.base.BaseController;

public class NewsDetailController extends BaseController{


    static final String NEWS_ID = "news_id";

    public static NewsDetailController newInstance(String newsId) {
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_ID,newsId);
        return new NewsDetailController(bundle);
    }


    public NewsDetailController(Bundle args) {
        super(args);
    }



    @Override
    protected int layoutRes() {
        return R.layout.layout_newsdetail;
    }
}

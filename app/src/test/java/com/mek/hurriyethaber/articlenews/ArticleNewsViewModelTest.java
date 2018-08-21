package com.mek.hurriyethaber.articlenews;

import com.mek.hurriyethaber.R;
import com.mek.hurriyethaber.articlenews.model.NewsModel;
import com.mek.hurriyethaber.data.ArticleNewsResponse;
import com.mek.hurriyethaber.testutil.TestUtils;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

public class ArticleNewsViewModelTest {

    private ArticleNewsViewModel viewmodel;

    @Before
    public void setUp() throws Exception {
        viewmodel = new ArticleNewsViewModel();
    }

    @Test
    public void getLoadingRelay() throws Exception {
        TestObserver<Boolean> loadingObserver = viewmodel.getLoadingRelay().test();
        viewmodel.loadingUpdated().accept(true);
        viewmodel.loadingUpdated().accept(false);

        loadingObserver.assertValues(true, false);
    }

    @Test
    public void getErrorRelay() throws Exception {

        TestObserver<Integer> errorObserver = viewmodel.getErrorRelay().test();
        viewmodel.onError().accept(new IOException());
        viewmodel.newsUpdated().accept(Collections.emptyList());

        errorObserver.assertValues(R.string.api_error,-1);

    }

    @Test
    public void getNewsRelay() throws Exception {


//        todo
//        ArticleNewsResponse response = TestUtils.loadJson("mock/get_articles.json", ArticleNewsResponse.class);
//        viewmodel.newsUpdated().accept(response.articles());
//
//        viewmodel.getNewsRelay().test().assertValue(response.articles());

    }
}
package com.mek.hurriyethaber.homecontroller;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.BundleCompat;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.mek.hurriyethaber.R;
import com.mek.hurriyethaber.articlenews.ArticleNewsController;
import com.mek.hurriyethaber.base.BaseController;
import com.mek.hurriyethaber.gallerycontoller.GalleryController;
import com.mek.hurriyethaber.ui.ScreenNavigator;
import com.mek.hurriyethaber.ui.TabScreenNav;
import com.mek.hurriyethaber.videocontroller.VideoController;

import java.util.Observable;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeController extends BaseController{


    @BindView(R.id.navigation)
    BottomNavigationView navigationView;
    @BindView(R.id.screen_container)
    ViewGroup screen_container;

    @Inject
    ScreenNavigator screenNavigator;

    @Inject
    TabScreenNav tabScreenNav;

    private SparseArray routerStates = new SparseArray<Bundle>();
    private Router router;


    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);

        router = getChildRouter(screen_container);
        tabScreenNav.initWithRouter(router);

        if (routerStates.size() == 0) {
            router.setRoot(RouterTransaction.with(new ArticleNewsController()));
        } else {
            router.rebindIfNeeded();
        }

        navigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.action_news:
                    router.setRoot(RouterTransaction.with(new ArticleNewsController()));
                    return true;

                case R.id.action_video:
                    router.setRoot(RouterTransaction.with(new VideoController()));
                    return true;

                case R.id.action_gallery:
                    router.setRoot(RouterTransaction.with(new GalleryController()));
                    return true;


            }
            return false;


        });

    }



    @Override
    protected int layoutRes() {
        return R.layout.layout_homecontroller;
    }






}

package com.mek.hurriyethaber.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.mek.hurriyethaber.R;
import com.mek.hurriyethaber.di.Injector;
import com.mek.hurriyethaber.di.ScreenInjector;
import com.mek.hurriyethaber.ui.ScreenNavigator;

import java.util.UUID;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {


    @Inject
    ScreenInjector screenInjector;
    @Inject
    ScreenNavigator screenNavigator;

    private String instaceID;
    private String INSTANCE_ID_KEY = "instance_id";


    private Router router;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            instaceID = savedInstanceState.getString(INSTANCE_ID_KEY);
        }else {
            instaceID = UUID.randomUUID().toString();
        }
        super.onCreate(savedInstanceState);
        Injector.inject(this);
        setContentView(layoutRes());
        ViewGroup screenContainer = findViewById(R.id.screen_container);
        if (screenContainer == null) {
            throw new NullPointerException("Her activity nin layres id si screen container olmalı");
        }

        router = Conductor.attachRouter(this,screenContainer,savedInstanceState);
        screenNavigator.initWithRouter(router,initialScreen());

        monitorBackStack();


    }

    private void monitorBackStack() {

        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(@Nullable Controller to, @Nullable Controller from, boolean isPush,
                                        @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {




            }

            @Override
            public void onChangeCompleted(@Nullable Controller to, @Nullable Controller from, boolean isPush,
                                          @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {

                if (!isPush && from != null){
                    Injector.clearComponent(from); //kullanıcı geri tusuna basarsa
                }

            }
        });

    }

    @LayoutRes
    protected abstract int layoutRes();

    protected abstract Controller initialScreen();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY,instaceID);
    }

    @Override
    public void onBackPressed() {

        if (!screenNavigator.pop()){
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        screenNavigator.clear();
        if (isFinishing()){
            Injector.clearComponent(this);
        }

    }

    public String getInstaceID() {
        return instaceID;
    }


    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }




}

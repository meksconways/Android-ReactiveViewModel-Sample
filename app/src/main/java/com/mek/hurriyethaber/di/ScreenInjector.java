package com.mek.hurriyethaber.di;

import android.app.Activity;
import android.content.Context;

import com.bluelinelabs.conductor.Controller;
import com.mek.hurriyethaber.base.BaseActivity;
import com.mek.hurriyethaber.base.BaseController;
import com.mek.hurriyethaber.base.MyApplication;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

public class ScreenInjector {


    private final Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjector;
    private final Map<String,AndroidInjector<Controller>> cache = new HashMap<>();


    @Inject
    ScreenInjector(Map<Class<? extends Controller>, Provider<AndroidInjector.Factory<? extends Controller>>> screenInjector){

        this.screenInjector = screenInjector;
    }

    void inject(Controller controller){

        if (!(controller instanceof BaseController)){
            throw new IllegalArgumentException("Controller base controlleri extend etmeli");
        }

        String instanceID = controller.getInstanceId();

        if (cache.containsKey(instanceID)){
            cache.get(instanceID).inject(controller);
            return;
        }

        //noinspection unchecked
        AndroidInjector.Factory<Controller> injectorFactory = (AndroidInjector.Factory<Controller>)
                screenInjector.get(controller.getClass()).get();

        AndroidInjector<Controller> injector = injectorFactory.create(controller);
        cache.put(instanceID,injector);
        Injector.inject(controller);

    }

    void clear(Controller controller){

        if (!(controller instanceof BaseController)){
            throw new IllegalArgumentException("bu activity BaseActivity den extend edilmeli");
        }

        cache.remove(controller.getInstanceId());
    }

    static ScreenInjector get(Activity activity){
        if (!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("bu activity BaseActivity den extend edilmeli");
        }

        return ((BaseActivity) activity).getScreenInjector();
    }




}

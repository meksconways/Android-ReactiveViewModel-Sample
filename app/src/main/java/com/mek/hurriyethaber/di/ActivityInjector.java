package com.mek.hurriyethaber.di;

import android.app.Activity;
import android.content.Context;

import com.mek.hurriyethaber.base.BaseActivity;
import com.mek.hurriyethaber.base.MyApplication;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

public class ActivityInjector {

    private final Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors;
    private final Map<String, AndroidInjector<? extends Activity>> cache = new HashMap<>();

    @Inject
    ActivityInjector(Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors){

        this.activityInjectors = activityInjectors;
    }

    void inject(Activity activity){ // bu şekilde tüm activityler otomatik olarak inject edilecek :) tek tek yazmaya gerenk yok ^^

        if (!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("bu activity BaseActivity den extend edilmeli");
        }

        String instanceID = ((BaseActivity) activity).getInstaceID();
        if (cache.containsKey(instanceID)){ // eğer mapte instance id varsa injectlenecek
            //noinspection unchecked
            ((AndroidInjector<Activity>) cache.get(instanceID)).inject(activity);
            return;
        }

        //noinspection unchecked
        AndroidInjector.Factory<Activity> injectorFactory = (AndroidInjector.Factory<Activity>)
                activityInjectors.get(activity.getClass()).get();

        AndroidInjector<Activity> injector = injectorFactory.create(activity);
        cache.put(instanceID,injector); // yoksa da eklenip injectlenicek
        Injector.inject(activity);

    }


    void clear(Activity activity){
        if (!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("bu activity BaseActivity den extend edilmeli");
        }

        cache.remove(((BaseActivity) activity).getInstaceID());

    }

    static ActivityInjector get(Context context){
        return ((MyApplication) context.getApplicationContext()).getActivityInjector();
    }


}

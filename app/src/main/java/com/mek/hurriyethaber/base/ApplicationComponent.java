package com.mek.hurriyethaber.base;

import com.mek.hurriyethaber.data.ApiServiceModule;
import com.mek.hurriyethaber.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ApiServiceModule.class,
        ServiceModule.class
})
public interface ApplicationComponent {


    void inject(MyApplication myApplication);
}

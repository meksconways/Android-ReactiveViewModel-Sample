package com.mek.hurriyethaber.networking;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;

@Module
public abstract class NetworkModule {


    @Provides
    @Singleton
    static Call.Factory provideOkHttp(){
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Named("base_url")
    static String provideBaseUrl(){
        return "https://api.hurriyet.com.tr/v1/";
    }



}

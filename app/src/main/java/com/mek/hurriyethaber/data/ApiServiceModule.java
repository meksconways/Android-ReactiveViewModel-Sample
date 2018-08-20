package com.mek.hurriyethaber.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class ApiServiceModule {


    @Provides
    @Singleton
    static ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }



}

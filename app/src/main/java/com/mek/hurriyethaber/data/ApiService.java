package com.mek.hurriyethaber.data;

import com.mek.hurriyethaber.articlenews.model.NewsModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiService {


    @GET("articles?$top=20")
    Single<List<NewsModel>> getArticles(@Header("apikey") String apikey);





}

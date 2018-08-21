package com.mek.hurriyethaber.data;

import com.mek.hurriyethaber.articlenews.model.NewsModel;
import com.mek.hurriyethaber.detail.model.NewsDetailModel;
import com.mek.hurriyethaber.util.AppHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiService {


    @GET("articles?$top=20")
    Single<List<NewsModel>> getArticles(@Header("apikey") String apikey);
    //todo değişebilir

    @GET("/v1/articles/{id}")
    Single<NewsDetailModel> getNewsDetail(@Header("apikey") String apikey,
                                          @Path("id") int id);





}

package com.mek.hurriyethaber.data;

import com.google.auto.value.AutoValue;
import com.mek.hurriyethaber.articlenews.model.NewsModel;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class ArticleNewsResponse {


    public abstract List<NewsModel> articles();

    //todo

    public static JsonAdapter<ArticleNewsResponse> jsonAdapter(Moshi moshi){
        return new AutoValue_ArticleNewsResponse.MoshiJsonAdapter(moshi);
    }

}

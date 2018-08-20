package com.mek.hurriyethaber.articlenews.model;

import java.util.List;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class NewsModel {

    @Json(name = "Id")
    public abstract String id();
    @Json(name = "ContentType")
    public abstract String contentType();
    @Json(name = "CreatedDate")
    public abstract String createdDate();
    @Json(name = "Description")
    public abstract String description();
    @Json(name = "Files")
    public abstract List<File> files();
    @Json(name = "ModifiedDate")
    public abstract String modifiedDate();
    @Json(name = "Path")
    public abstract String path();
    @Json(name = "StartDate")
    public abstract String startDate();
    @Json(name = "Tags")
    public abstract List<String> tags();
    @Json(name = "Title")
    public abstract String title();
    @Json(name = "Url")
    public abstract String url();


    public static JsonAdapter<NewsModel> jsonAdapter(Moshi moshi){
        return new AutoValue_NewsModel.MoshiJsonAdapter(moshi);
    }


}

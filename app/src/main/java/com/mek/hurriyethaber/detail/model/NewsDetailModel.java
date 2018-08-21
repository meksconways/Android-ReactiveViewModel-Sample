package com.mek.hurriyethaber.detail.model;

import java.util.List;

import com.google.auto.value.AutoValue;
import com.mek.hurriyethaber.articlenews.model.File;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class NewsDetailModel {

    @Json(name = "Id")
    public abstract String id();
    @Json(name = "ContentType")
    public abstract String contentType();
    @Json(name = "CreatedDate")
    abstract String createdDate();
    @Json(name = "Description")
    abstract String description();
    @Json(name = "Editor")
    abstract String editor();
    @Json(name = "Files")
    public abstract List<File> files();
    @Json(name = "ModifiedDate")
    abstract String modifiedDate();
    @Json(name = "Path")
    abstract String path();
    @Json(name = "RelatedNews")
    abstract List<Object> relatedNews();
    @Json(name = "StartDate")
    abstract String startDate();
    @Json(name = "Tags")
    abstract List<String> tags();
    @Json(name = "Text")
    public abstract String text();
    @Json(name = "Title")
    public abstract String title();
    @Json(name = "Url")
    public abstract String url();
    @Json(name = "Writers")
    abstract List<Object> writers();


    public static JsonAdapter<NewsDetailModel> jsonAdapter(Moshi moshi){
        return new AutoValue_NewsDetailModel.MoshiJsonAdapter(moshi);
    }

}

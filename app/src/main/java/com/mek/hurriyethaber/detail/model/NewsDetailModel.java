package com.mek.hurriyethaber.detail.model;

import java.util.List;

import com.google.auto.value.AutoValue;
import com.mek.hurriyethaber.articlenews.model.File;
import com.squareup.moshi.Json;

@AutoValue
public abstract class NewsDetailModel {

    @Json(name = "Id")
    abstract String id();
    @Json(name = "ContentType")
    abstract String contentType();
    @Json(name = "CreatedDate")
    abstract String createdDate();
    @Json(name = "Description")
    abstract String description();
    @Json(name = "Editor")
    abstract String editor();
    @Json(name = "Files")
    abstract List<File> files();
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
    abstract String text();
    @Json(name = "Title")
    abstract String title();
    @Json(name = "Url")
    abstract String url();
    @Json(name = "Writers")
    abstract List<Object> writers();

}

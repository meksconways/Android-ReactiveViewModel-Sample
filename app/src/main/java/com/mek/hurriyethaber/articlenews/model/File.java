package com.mek.hurriyethaber.articlenews.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class File {

    @Json(name = "FileUrl")
    public abstract String fileUrl();
    @Json(name = "Metadata")
    public abstract Metadata metadata();

    public static JsonAdapter<File> jsonAdapter(Moshi moshi){
        return new AutoValue_File.MoshiJsonAdapter(moshi);
    }

}
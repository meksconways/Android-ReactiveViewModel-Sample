package com.mek.hurriyethaber.articlenews.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Metadata {

    @Json(name = "Title")
    public abstract String title();
    @Json(name = "Description")
    public abstract String description();


    public static JsonAdapter<Metadata> jsonAdapter(Moshi moshi){
        return new AutoValue_Metadata.MoshiJsonAdapter(moshi);
    }

}


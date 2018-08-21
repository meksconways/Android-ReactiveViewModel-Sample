package com.mek.hurriyethaber.detail;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;

import javax.annotation.Nullable;

@AutoValue
public abstract class NewsDetailState {


    abstract Boolean loading();

    @Nullable
    @Json(name = "Text")
    abstract String text();

    @Nullable
    @Json(name = "Title")
    abstract String title();

    @Nullable
    @Json(name = "FileUrl")
    public abstract String fileUrl();

    @Nullable
    abstract Integer errorText();

    static Builder builder(){
        return new AutoValue_NewsDetailState.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder{

        abstract Builder loading(Boolean loading);

        abstract Builder title(String title);

        abstract Builder text(String text);

        abstract Builder fileUrl(String fileurl);

        abstract Builder errorText(Integer errortext);

        abstract NewsDetailState build();

    }


}

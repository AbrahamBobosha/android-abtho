package com.example.motivation.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Movie {

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("poster_path")
    private String img_url;

    @SerializedName("id")
    private long id;

    @SerializedName("runtime")
    private long runtime;

    public long getRuntime() {
        return runtime;
    }

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String release_date;

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getImg_url() {
        return img_url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }


}

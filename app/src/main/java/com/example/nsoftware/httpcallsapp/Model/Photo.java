package com.example.nsoftware.httpcallsapp.Model;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;
    @SerializedName("url")
    private String url;
    @SerializedName("title")
    private String title;
    @SerializedName("id")
    private int id;
    @SerializedName("albumId")
    private int albumId;

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public int getAlbumId() {
        return albumId;
    }
}

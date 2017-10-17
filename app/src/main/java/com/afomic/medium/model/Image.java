package com.afomic.medium.model;

/**
 * Created by afomic on 10/16/17.
 */

public class Image implements Html {
    private String imageUrl;
    public Image(String url){
        imageUrl=url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int getType() {
        return Html.TAG_IMAGE;
    }

}

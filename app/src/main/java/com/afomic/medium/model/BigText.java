package com.afomic.medium.model;

/**
 * Created by afomic on 10/16/17.
 */

public class BigText implements Html {
    private String bodyText;

    public BigText(String text){
        bodyText=text;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    @Override
    public int getType() {
        return Html.TAG_BIG_TEXT;
    }
}

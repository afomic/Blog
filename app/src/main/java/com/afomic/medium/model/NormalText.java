package com.afomic.medium.model;

import com.afomic.medium.model.Html;

/**
 * Created by afomic on 10/16/17.
 *
 */

public class NormalText implements Html {
    private String textBody;
    public NormalText(String text){
        textBody=text;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    @Override
    public int getType() {
        return Html.TAG_NORMAL_TEXT;
    }

    @Override
    public String toHtml() {
        return "<nt>"+textBody+"</nt>";
    }
}

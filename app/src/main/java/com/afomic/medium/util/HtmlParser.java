package com.afomic.medium.util;


import com.afomic.medium.model.Html;

import java.util.ArrayList;

/**
 * Created by afomic on 10/18/17.
 */

public class HtmlParser {
    public static ArrayList<Html> fromHtml(String html){
        MyTagHandler tagHandler=new MyTagHandler();
        android.text.Html.fromHtml(html,null,tagHandler);
        return tagHandler.getHtmlList();
    }
    public static String toHtml(ArrayList<Html> htmlArrayList){
        String html ="<body>";
        for(Html element:htmlArrayList){
            html=html+element.toHtml();
        }
        html=html+"</body>";
        return html;
    }
}

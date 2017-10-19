package com.afomic.medium.util;

import android.text.Editable;
import android.text.Html;
import android.util.Log;

import com.afomic.medium.model.BigText;
import com.afomic.medium.model.Image;
import com.afomic.medium.model.NormalText;

import org.xml.sax.XMLReader;

import java.util.ArrayList;

/**
 * Created by afomic on 10/18/17.
 */

public class MyTagHandler implements Html.TagHandler {
    private static final int TAG_NORMAL_TEXT=1;
    private static final int TAG_BIG_TEXT=2;
    private static final int TAG_IMAGE=3;

    private int type=-1;
    private int start=-1;
    ArrayList<com.afomic.medium.model.Html> htmlList;
    public MyTagHandler(){
        new ArrayList<>();
    }
    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if(opening){
            start=output.length();
            if(tag.equals("nt")){
                type=TAG_NORMAL_TEXT;
            }else if(tag.equals("bt")){
                type=TAG_BIG_TEXT;
            }else if(tag.equals("imv")){
                type=TAG_IMAGE;
            }
        }else {
            switch (type){
                case TAG_BIG_TEXT:
                    String bigTextContent=output.subSequence(start,output.length()).toString();
                    BigText bigText=new BigText(bigTextContent);
                    htmlList.add(bigText);
                    break;
                case TAG_NORMAL_TEXT:
                    String normalTextContent=output.subSequence(start,output.length()).toString();
                    NormalText normalText=new NormalText(normalTextContent);
                    htmlList.add(normalText);
                    break;
                case TAG_IMAGE:
                    String imageUrl=output.subSequence(start,output.length()).toString();
                    Image image=new Image(imageUrl);
                    htmlList.add(image);
                    break;

            }

        }

    }
    public ArrayList<com.afomic.medium.model.Html> getHtmlList(){
        return htmlList;
    }
}

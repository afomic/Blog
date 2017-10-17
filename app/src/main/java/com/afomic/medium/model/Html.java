package com.afomic.medium.model;

/**
 * Created by afomic on 10/16/17.
 */

public interface Html {
    public static final int TAG_NORMAL_TEXT=0;
    public static final int TAG_IMAGE=1;
    public static final int TAG_BIG_TEXT=2;
    /**
     * this return the type of Html tag we
     */
    int getType();
}

package com.afomic.medium.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by afomic on 10/19/17.
 */

public class Post implements Parcelable{
    private String title;
    private String pictureUrl;
    private String posterName;
    private String posterIconUrl;
    private long time;
    private String html;

    public Post(){

    }

    protected Post(Parcel in) {
        title = in.readString();
        pictureUrl = in.readString();
        posterName = in.readString();
        posterIconUrl = in.readString();
        time = in.readLong();
        html = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(pictureUrl);
        dest.writeString(posterName);
        dest.writeString(posterIconUrl);
        dest.writeLong(time);
        dest.writeString(html);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPosterIconUrl() {
        return posterIconUrl;
    }

    public void setPosterIconUrl(String posterIconUrl) {
        this.posterIconUrl = posterIconUrl;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}

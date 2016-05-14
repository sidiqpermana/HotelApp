package com.sidiq.intel.hotelapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by inte on 5/12/2016.
 */
public class GalleryItem implements Parcelable {
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imagePath);
    }

    public GalleryItem() {
    }

    protected GalleryItem(Parcel in) {
        this.imagePath = in.readString();
    }

    public static final Parcelable.Creator<GalleryItem> CREATOR = new Parcelable.Creator<GalleryItem>() {
        @Override
        public GalleryItem createFromParcel(Parcel source) {
            return new GalleryItem(source);
        }

        @Override
        public GalleryItem[] newArray(int size) {
            return new GalleryItem[size];
        }
    };
}

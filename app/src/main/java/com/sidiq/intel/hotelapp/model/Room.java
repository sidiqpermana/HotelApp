package com.sidiq.intel.hotelapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inte on 5/11/2016.
 */
public class Room implements Parcelable {
    @SerializedName("roomName")
    private String name;
    @SerializedName("remarks")
    private String remarks;
    @SerializedName("rate")
    private String rate;
    @SerializedName("photo")
    private String photo;
    @SerializedName("idRoom")
    private int roomId;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.remarks);
        dest.writeString(this.rate);
        dest.writeString(this.photo);
        dest.writeInt(this.roomId);
    }

    public Room() {
    }

    protected Room(Parcel in) {
        this.name = in.readString();
        this.remarks = in.readString();
        this.rate = in.readString();
        this.photo = in.readString();
        this.roomId = in.readInt();
    }

    public static final Parcelable.Creator<Room> CREATOR = new Parcelable.Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel source) {
            return new Room(source);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };
}

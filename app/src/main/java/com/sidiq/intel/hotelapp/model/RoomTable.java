package com.sidiq.intel.hotelapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by inte on 5/13/2016.
 */
public class RoomTable extends RealmObject implements Parcelable {
    private long id;
    private String name;
    private String remarks;
    private String rate;
    private String photo;
    private int roomId;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.remarks);
        dest.writeString(this.rate);
        dest.writeString(this.photo);
        dest.writeInt(this.roomId);
    }

    public RoomTable() {
    }

    protected RoomTable(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.remarks = in.readString();
        this.rate = in.readString();
        this.photo = in.readString();
        this.roomId = in.readInt();
    }

    public static final Parcelable.Creator<RoomTable> CREATOR = new Parcelable.Creator<RoomTable>() {
        @Override
        public RoomTable createFromParcel(Parcel source) {
            return new RoomTable(source);
        }

        @Override
        public RoomTable[] newArray(int size) {
            return new RoomTable[size];
        }
    };
}

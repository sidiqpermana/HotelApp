package com.sidiq.intel.hotelapp.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inte on 5/13/2016.
 */
public class BaseResponse implements Parcelable {
    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.message);
    }

    public BaseResponse() {
    }

    protected BaseResponse(Parcel in) {
        this.status = in.readString();
        this.message = in.readString();
    }

}

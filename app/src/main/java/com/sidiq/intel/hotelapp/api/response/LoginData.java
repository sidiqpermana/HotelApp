package com.sidiq.intel.hotelapp.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inte on 5/13/2016.
 */
public class LoginData implements Parcelable {
    @SerializedName("idUser")
    private String idUser;
    @SerializedName("username")
    private String username;
    @SerializedName("passwd")
    private String passwd;
    @SerializedName("fullname")
    private String fullName;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idUser);
        dest.writeString(this.username);
        dest.writeString(this.passwd);
        dest.writeString(this.fullName);
    }

    public LoginData() {
    }

    protected LoginData(Parcel in) {
        this.idUser = in.readString();
        this.username = in.readString();
        this.passwd = in.readString();
        this.fullName = in.readString();
    }

    public static final Parcelable.Creator<LoginData> CREATOR = new Parcelable.Creator<LoginData>() {
        @Override
        public LoginData createFromParcel(Parcel source) {
            return new LoginData(source);
        }

        @Override
        public LoginData[] newArray(int size) {
            return new LoginData[size];
        }
    };
}

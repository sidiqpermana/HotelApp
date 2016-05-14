package com.sidiq.intel.hotelapp.api.response;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by inte on 5/13/2016.
 */
public class LoginResponse extends BaseResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("data")
    private LoginData loginData;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.token);
        dest.writeParcelable(this.loginData, flags);
    }

    public LoginResponse() {
    }

    protected LoginResponse(Parcel in) {
        super(in);
        this.token = in.readString();
        this.loginData = in.readParcelable(LoginData.class.getClassLoader());
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel source) {
            return new LoginResponse(source);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };
}

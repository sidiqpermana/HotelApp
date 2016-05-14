package com.sidiq.intel.hotelapp.api.request;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sidiq.intel.hotelapp.api.ApiService;
import com.sidiq.intel.hotelapp.api.response.LoginResponse;

import cz.msebera.android.httpclient.Header;

/**
 * Created by inte on 5/13/2016.
 */
public class LoginRequest {
    private AsyncHttpClient asyncHttpClient;
    private OnLoginRequestListener onLoginRequestListener;

    public LoginRequest(){
        asyncHttpClient = new AsyncHttpClient();
    }

    public OnLoginRequestListener getOnLoginRequestListener() {
        return onLoginRequestListener;
    }

    public void setOnLoginRequestListener(OnLoginRequestListener onLoginRequestListener) {
        this.onLoginRequestListener = onLoginRequestListener;
    }

    public void callApi(RequestParams params){
        asyncHttpClient.post(ApiService.POST_LOGIN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Log.d("res", response);
                if (statusCode == 200 && !TextUtils.isEmpty(response)){
                    Gson gson = new Gson();
                    LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
                    if (loginResponse.getStatus().equals("200")){
                        getOnLoginRequestListener().onLoginRequestListenerSuccess(loginResponse);
                    }else{
                        getOnLoginRequestListener().onLoginRequestListenerFailed("Password Salah");
                    }
                }else{
                    getOnLoginRequestListener().onLoginRequestListenerFailed("Login Error");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                getOnLoginRequestListener().onLoginRequestListenerFailed("Login Error");
            }
        });
    }

    public void cancel(){
        if (asyncHttpClient != null){
            asyncHttpClient.cancelAllRequests(true);
        }
    }

    public interface OnLoginRequestListener{
        void onLoginRequestListenerSuccess(LoginResponse loginResponse);
        void onLoginRequestListenerFailed(String errorResponse);
    }
}

package com.sidiq.intel.hotelapp.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by inte on 5/13/2016.
 */
public class AppPreference {
    private String KEY_NAME = "name";
    private String KEY_TOKEN = "token";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Activity activity;

    public AppPreference(Activity activity){
        this.activity = activity;
        sharedPreferences = activity.getSharedPreferences("hotelapp", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setName(String name){
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public String getName(){
        return sharedPreferences.getString(KEY_NAME, "");
    }

    public void setToken(String token){
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public String getToken(){
        return sharedPreferences.getString(KEY_TOKEN, "");
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }
}

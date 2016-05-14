package com.sidiq.intel.hotelapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.sidiq.intel.hotelapp.helper.AppPreference;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new DelayAsync().execute();
    }

    private class DelayAsync extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try{
                Thread.sleep(3000);
            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            AppPreference appPreference = new AppPreference(SplashscreenActivity.this);
            if (!TextUtils.isEmpty(appPreference.getToken())){
                Intent intent = new Intent(SplashscreenActivity.this, HomeActivity.class);
                startActivity(intent);
            }else {
                Intent mIntent = new Intent(SplashscreenActivity.this, LoginActivity.class);
                startActivity(mIntent);
            }
            finish();
        }
    }
}

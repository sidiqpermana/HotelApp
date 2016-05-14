package com.sidiq.intel.hotelapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.sidiq.intel.hotelapp.api.request.LoginRequest;
import com.sidiq.intel.hotelapp.api.response.LoginResponse;
import com.sidiq.intel.hotelapp.helper.AppPreference;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener,
        LoginRequest.OnLoginRequestListener{
    private Button btnLogin;
    private EditText edtEmail;
    private EditText edtPassword;
    private LoginRequest loginRequest;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Sign in");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtEmail = (EditText)findViewById(R.id.edt_email);
        edtPassword = (EditText)findViewById(R.id.edt_password);
        btnLogin = (Button)findViewById(R.id.btn_sign_in);
        btnLogin.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        loginRequest = new LoginRequest();
        loginRequest.setOnLoginRequestListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sign_in){
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_LONG).show();
            }else{
                progressDialog.show();
                RequestParams params = new RequestParams();
                params.put("user", email);
                params.put("pass", password);

                loginRequest.callApi(params);
            }
        }
    }

    @Override
    public void onLoginRequestListenerSuccess(LoginResponse loginResponse) {
        progressDialog.dismiss();

        AppPreference appPreference = new AppPreference(LoginActivity.this);
        appPreference.setName(loginResponse.getLoginData().getFullName());
        appPreference.setToken(loginResponse.getToken());

        Intent intent = new Intent(LoginActivity.this,
                HomeActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onLoginRequestListenerFailed(String errorResponse) {
        progressDialog.dismiss();

        Toast.makeText(LoginActivity.this, errorResponse, Toast.LENGTH_LONG).show();
    }
}

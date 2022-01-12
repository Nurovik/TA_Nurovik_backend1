package com.moblile.blfc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moblile.blfc.R;
import com.moblile.blfc.ResponseServer.ResponseLogin;
import com.moblile.blfc.Session.SessionManager;
import com.moblile.blfc.StoreApi.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin, btnback;
    SessionManager mSession;
    Api api = new Api();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSession = new SessionManager(getApplicationContext());
        mSession.checkLogin();


        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnback = (Button) findViewById(R.id.btn_back);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etUsername.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username Harus Diisi", Toast.LENGTH_LONG).show();
                    return;
                } if(etPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password Harus Diisi", Toast.LENGTH_LONG).show();
                    return;
                }

                String username, password;


                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                login(username, password);
//
            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), ActivityNews.class);
                startActivity(in);
            }
        });
    }


    private void login(final String username, final String password)
    {
        TextView pesan;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.view_loading_dialog);
//        dialog.setMessage("Mengambil data...");
        dialog.show();

        api.loginapi( username, password, new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                dialog.dismiss();
                if (response.code() != 200)
                {
                    Toast.makeText(ActivityLogin.this, "Not Respon", Toast.LENGTH_SHORT).show();
//                    tvPesan.setText("ERROR RESPOND : " + response.code());
                }
                else {
                    String token="", message="", statusCode="";
                    statusCode = response.body().getStatusCode();
                    message= response.body().getStatus();
                    String text = message;
                    if (statusCode.equals("00"))
                    {
                        token = response.body().getToken();

//                        if (level.equalsIgnoreCase("1"))
//                        {
                            doLogin(username, password, token);
                            Log.d("TOKEN", token);
//                        }
//                        else
//                        {
//                            text = "Anda tidak dapat Login melalui Mobile";
//                        }

                    }
                    Toast.makeText(ActivityLogin.this, text, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

                dialog.dismiss();
                mSession.logoutUser();
                Toast.makeText(ActivityLogin.this, "Gagal", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void doLogin(String username, String password, String token)
    {
        mSession.createLoginSession(username, password, token);
        mSession.checkLogin();
    }
}
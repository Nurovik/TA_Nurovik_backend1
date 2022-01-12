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
import com.moblile.blfc.Model.Jadwal;
import com.moblile.blfc.R;
import com.moblile.blfc.ResponseServer.ResponseServer;
import com.moblile.blfc.Session.SessionManager;
import com.moblile.blfc.StoreApi.Api;
import com.moblile.blfc.Util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityEditScore extends AppCompatActivity {

    TextView tv_namaliga,tv_teamA,tv_teamB;
    EditText editscoreA, editscoreB;
    Button Updatescore;

    SessionManager mSession;
    Api api = new Api();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_score);
        mSession = new SessionManager(getApplicationContext());
        final Bundle mData = getIntent().getExtras();
        String sjJSON = mData.getString("data");
        final Jadwal mjadwal = Utils.getGsonParser().fromJson(sjJSON, Jadwal.class);


        tv_namaliga = (TextView) findViewById(R.id.tv_namaLigadashboard);
        tv_teamA = (TextView) findViewById(R.id.tv_teamA);
        tv_teamB = (TextView) findViewById(R.id.tv_teamB);
        editscoreA = (EditText) findViewById(R.id.ScoreA);
        editscoreB = (EditText) findViewById(R.id.ScoreB);
        Updatescore = (Button) findViewById(R.id.Updatescore);


        tv_namaliga.setText(mjadwal.getNamaliga());
        tv_teamA.setText(mjadwal.getTeam1());
        tv_teamB.setText(mjadwal.getTeam2());
        editscoreA.setText(String.valueOf(mjadwal.getGoal1()));
        editscoreB.setText(String.valueOf(mjadwal.getGoal2()));


        Updatescore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editscoreA.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Score  Harus Diisi", Toast.LENGTH_LONG).show();
                    return;
                } if(editscoreB.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Score Harus Diisi", Toast.LENGTH_LONG).show();
                    return;
                }

                String scoreA, scoreB;

                scoreA = editscoreA.getText().toString();
                scoreB = editscoreB.getText().toString();
                mjadwal.setGoal1(Integer.parseInt(scoreA));
                mjadwal.setGoal2(Integer.parseInt(scoreB));

                final String token = mSession.getToken();
                final String user = mSession.getUsername();
                update(token, user, String.valueOf(mjadwal.getId()),mjadwal, String.valueOf(mjadwal.getIdliga()));


            }
        });
    }


    public void update(String token, String user, String id, Jadwal req, String idliga) {

        Log.d("TOKEN", "getData: " + token);
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.view_loading_dialog);
        dialog.show();


        api.UpdateRequest(req, user, token, id, idliga, new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                dialog.dismiss();
                String message, statusCode;

                if (response.code() != 200) {
                    message = "Server Not Respond";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();


                } else {
                    message = response.body().getStatus();
                    //berhasil delete data
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtras(args);
                    startActivity(intent);

                }

            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                dialog.dismiss();
                mSession.logoutUser();
                Toast.makeText(getApplicationContext(), "Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERR", "onFailure: " + t.getMessage());

            }
        });

    }
}
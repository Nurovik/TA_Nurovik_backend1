package com.moblile.blfc.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.moblile.blfc.Adapter.AdapterListJadwal;
import com.moblile.blfc.Adapter.AdapterListKlasmen;
import com.moblile.blfc.Model.Jadwal;
import com.moblile.blfc.Model.Klasmen;
import com.moblile.blfc.R;
import com.moblile.blfc.ResponseServer.ResponseJadwal;
import com.moblile.blfc.ResponseServer.ResponseKlasmen;
import com.moblile.blfc.StoreApi.Api;
import com.moblile.blfc.Util.ItemAnimation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityJadwal extends AppCompatActivity {

    private View parent_view;
    Api api = new Api();
    private RecyclerView recyclerView;
    private AdapterListJadwal mAdapter;
    private List<Jadwal> items = new ArrayList<>();
    private int animation_type = ItemAnimation.LEFT_RIGHT;
    LinearLayout llKosong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        llKosong = (LinearLayout)findViewById(R.id.ll_kosong);
        getSupportActionBar().setTitle("jadwal");
        getDataJadwal();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_refresh:
                getDataJadwal();
                break;
            case R.id.action_mode:
                showSingleChoiceDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final String[] ANIMATION_TYPE = new String[]{
            "News", "pemain", "klasmen", "Login"
    };

    private void showSingleChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Menu");
        builder.setCancelable(false);
        builder.setSingleChoiceItems(ANIMATION_TYPE, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String selected = ANIMATION_TYPE[i];
                if (selected.equalsIgnoreCase("News")) {

                    Intent in = new Intent(getApplicationContext(), ActivityNews.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(in);

                } else if (selected.equalsIgnoreCase("pemain")) {

                    Intent in = new Intent(getApplicationContext(), ActivityPemain.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(in);

                } else if (selected.equalsIgnoreCase("klasmen")) {

                    Intent in = new Intent(getApplicationContext(), ActivityKlasmen.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(in);

                } else if (selected.equalsIgnoreCase("Login")) {

                    Intent in = new Intent(getApplicationContext(), ActivityLogin.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(in);

                }

                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    //getData klasmen From API
    private void getDataJadwal()
    {

        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.view_loading_dialog);
        dialog.show();

        api.jadwal(new Callback<ResponseJadwal>() {

            @Override
            public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {

                dialog.dismiss();
                String message, statusCode;
                if (response.code() != 200)
                {
                    message = "Server Not Respond";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
                else
                {
                    message = response.body().getStatus();
                    statusCode = response.body().getStatusCode();


                    if (statusCode.equals("00"))
                    {
                        // Berhasil dapat data

                        List<Jadwal> listDataJadwal= new ArrayList<>();
                        listDataJadwal = response.body().getListData();

                        Gson gson = new Gson();
                        Log.d("sucees", "jadwal: " + gson.toJson(listDataJadwal));
                        setAdapter(listDataJadwal);

                    }
                    else if(statusCode.equals("02"))
                    {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                    else
                    {



                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseJadwal> call, Throwable t) {
                dialog.dismiss();
                System.out.println( "ini gagal " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERR", "onFailure: " + t.getMessage());

            }
        });


    }

    private void setAdapter(List<Jadwal> items) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewjadwal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(items == null){
            recyclerView.setAdapter(null);
            llKosong.setVisibility(View.VISIBLE);
        }else{
            //set data and list adapter
            mAdapter = new AdapterListJadwal(this, items, animation_type);
            recyclerView.setAdapter(mAdapter);

            // on item list clicked
//            mAdapter.setOnItemClickListener(new AdapterListKlasmen.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(View view, Klasmen obj, int position) {
//
//                }
//            });

            llKosong.setVisibility(View.GONE);

        }





    }
}
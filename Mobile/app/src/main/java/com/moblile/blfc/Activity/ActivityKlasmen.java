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
import com.moblile.blfc.Adapter.AdapterListKlasmen;
import com.moblile.blfc.Model.Klasmen;
import com.moblile.blfc.R;
import com.moblile.blfc.ResponseServer.ResponseKlasmen;
import com.moblile.blfc.StoreApi.Api;
import com.moblile.blfc.Util.ItemAnimation;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityKlasmen extends AppCompatActivity {

    private View parent_view;
    Api api = new Api();
    private RecyclerView recyclerView;
    private AdapterListKlasmen mAdapter;
    private List<Klasmen> items = new ArrayList<>();
    private int animation_type = ItemAnimation.LEFT_RIGHT;
    LinearLayout llKosong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klasmen);
        parent_view = findViewById(android.R.id.content);

        llKosong = (LinearLayout)findViewById(R.id.ll_kosong);
        getSupportActionBar().setTitle("Klasmen");
        getDataklasmen();
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
                getDataklasmen();
                break;
            case R.id.action_mode:
                showSingleChoiceDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final String[] ANIMATION_TYPE = new String[]{
            "News", "pemain", "jadwal", "login"
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
                } else if (selected.equalsIgnoreCase("jadwal")) {

                    Intent in = new Intent(getApplicationContext(), ActivityJadwal.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(in);

                } else if (selected.equalsIgnoreCase("login")) {

                }

                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    //getData klasmen From API
    private void getDataklasmen()
    {

        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.view_loading_dialog);
        dialog.show();

        api.klasmen(new Callback<ResponseKlasmen>() {

            @Override
            public void onResponse(Call<ResponseKlasmen> call, Response<ResponseKlasmen> response) {

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

                        List<Klasmen> listDataklasmen= new ArrayList<>();
                        listDataklasmen = response.body().getListData();

                        Gson gson = new Gson();
                        Log.d("sucees", "liga: " + gson.toJson(listDataklasmen));
                        setAdapter(listDataklasmen);

                    }
                    else if(statusCode.equals("02"))
                    {
                        // Token bermasalah (Expired, tidak sesuai, dll) -> Arahkan ke logout, login ulang
//                        mSession.logoutUser();
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                    else
                    {

//                        rvListproyek.setAdapter(null);
//                        setAdapter(null);
//                        llKosong.setVisibility(View.VISIBLE);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKlasmen> call, Throwable t) {
                dialog.dismiss();
                System.out.println( "ini gagal " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERR", "onFailure: " + t.getMessage());

            }
        });


    }

    private void setAdapter(List<Klasmen> items) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewklasmen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(items == null){
            recyclerView.setAdapter(null);
            llKosong.setVisibility(View.VISIBLE);
        }else{
            //set data and list adapter
            mAdapter = new AdapterListKlasmen(this, items, animation_type);
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
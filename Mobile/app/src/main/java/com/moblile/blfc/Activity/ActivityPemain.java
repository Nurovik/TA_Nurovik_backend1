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
import com.moblile.blfc.Adapter.AdapterListPemain;
import com.moblile.blfc.Model.Pemain;
import com.moblile.blfc.R;
import com.moblile.blfc.ResponseServer.ResponsePemain;
import com.moblile.blfc.StoreApi.Api;
import com.moblile.blfc.Util.ItemAnimation;
import com.moblile.blfc.Util.Utils;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPemain extends AppCompatActivity {
    private View parent_view;
    Api api = new Api();
    private RecyclerView recyclerView;
    private AdapterListPemain mAdapter;
    private List<Pemain> items = new ArrayList<>();
    private int animation_type = ItemAnimation.LEFT_RIGHT;
    LinearLayout llKosong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemain);

        parent_view = findViewById(android.R.id.content);

        llKosong = (LinearLayout)findViewById(R.id.ll_kosong);
        getSupportActionBar().setTitle("Pemain");
        getDatapemain();
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
                getDatapemain();
                break;
            case R.id.action_mode:
                showSingleChoiceDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final String[] ANIMATION_TYPE = new String[]{
            "News","klasmen",  "jadwal", "Right to Left"
    };

    private void showSingleChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Menu");
        builder.setCancelable(false);
        builder.setSingleChoiceItems(ANIMATION_TYPE, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String selected = ANIMATION_TYPE[i];
                if (selected.equalsIgnoreCase("klasmen")) {

                    Intent in = new Intent(getApplicationContext(), ActivityKlasmen.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    getApplicationContext().startActivity(in);
//                    animation_type = ItemAnimation.BOTTOM_UP;
                } else if (selected.equalsIgnoreCase("News")) {
                    Intent in = new Intent(getApplicationContext(), ActivityNews.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(in);
                } else if (selected.equalsIgnoreCase("jadwal")) {

                    Intent in = new Intent(getApplicationContext(), ActivityJadwal.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(in);
                } else if (selected.equalsIgnoreCase("login")) {
//                    animation_type = ItemAnimation.RIGHT_LEFT;
                }
//                getSupportActionBar().setTitle(selected);
//                setAdapter();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    //getData pemain From API
    private void getDatapemain()
    {

        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.view_loading_dialog);
        dialog.show();

        api.pemain(new Callback<ResponsePemain>() {

            @Override
            public void onResponse(Call<ResponsePemain> call, Response<ResponsePemain> response) {

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

                        List<Pemain> listDatapemain= new ArrayList<>();
                        listDatapemain  = response.body().getListData();


                        setAdapter(listDatapemain);


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
            public void onFailure(Call<ResponsePemain> call, Throwable t) {
                dialog.dismiss();
                System.out.println( "ini gagal " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERR", "onFailure: " + t.getMessage());

            }
        });


    }

    private void setAdapter(List<Pemain> items) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(items == null){
            recyclerView.setAdapter(null);
            llKosong.setVisibility(View.VISIBLE);
        }else{
            //set data and list adapter
            mAdapter = new AdapterListPemain(this, items, animation_type);
            recyclerView.setAdapter(mAdapter);

            // on item list clicked
            mAdapter.setOnItemClickListener(new AdapterListPemain.OnItemClickListener() {
                @Override
                public void onItemClick(View view, Pemain obj, int position) {

                    Intent in = new Intent(getApplicationContext(), ActivityPemainDetail.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    final Bundle args = new Bundle();
                    args.putString("datapemain", Utils.getGsonParser().toJson(obj));
                    in.putExtras(args);
                    getApplicationContext().startActivity(in);
//                    Snackbar.make(parent_view, "Item " + obj.getTitle() + " clicked", Snackbar.LENGTH_SHORT).show();
                }
            });

            llKosong.setVisibility(View.GONE);

        }





    }
}
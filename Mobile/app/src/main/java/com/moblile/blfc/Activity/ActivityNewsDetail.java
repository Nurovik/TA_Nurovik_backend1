package com.moblile.blfc.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.moblile.blfc.Model.News;
import com.moblile.blfc.R;
import com.moblile.blfc.ResponseServer.ResponseNewsDetail;
import com.moblile.blfc.StoreApi.Api;
import com.moblile.blfc.Util.ConvertDate;
import com.moblile.blfc.Util.ItemAnimation;
import com.moblile.blfc.Util.Utils;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityNewsDetail extends AppCompatActivity {
    Api api = new Api();

    TextView mtitle,mpriview,content,datecreated;
    ImageView image;
   private String idNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);



        getSupportActionBar().setTitle("News Detail");

        mtitle = (TextView) findViewById(R.id.title);
        mpriview = (TextView) findViewById(R.id.preview);
        content = (TextView) findViewById(R.id.content);
        image = (ImageView) findViewById(R.id.image);
        datecreated= (TextView) findViewById(R.id.datecreated);
        final Bundle mData = getIntent().getExtras();
        this.idNews = mData.getString("id");
        getData(idNews);

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
                getData(String.valueOf(idNews));
                break;
            case R.id.action_mode:
                showSingleChoiceDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final String[] ANIMATION_TYPE = new String[]{
            "klasmen", "pemain", "News", "Login"
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

                } else if (selected.equalsIgnoreCase("pemain")) {
                    Intent in = new Intent(getApplicationContext(), ActivityPemain.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(in);
                } else if (selected.equalsIgnoreCase("Login")) {

                    Intent in = new Intent(getApplicationContext(), ActivityLogin.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(in);

                } else if (selected.equalsIgnoreCase("News")) {

                    Intent in = new Intent(getApplicationContext(), ActivityNews.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    getApplicationContext().startActivity(in);

                }
//                getSupportActionBar().setTitle(selected);
//                setAdapter();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    //getData News From API
    private void getData(String id)
    {

        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.view_loading_dialog);
        dialog.show();

        api.getrequestNesDetail(id,new Callback<ResponseNewsDetail>() {

            @Override
            public void onResponse(Call<ResponseNewsDetail> call, Response<ResponseNewsDetail> response) {

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
                        ConvertDate convert = new ConvertDate();
                        News mNews= new News();
                        mNews = response.body().getData();

                        mtitle.setText(mNews.getTitle());
                        mpriview.setText(mNews.getPriview());
                        content.setText(mNews.getContent());
                        datecreated.setText(convert.convertlongtodate(mNews.getDatecreated()));
                        Picasso.with(getApplicationContext()).load(Utils.BaseUrlcms+mNews.getImage()).into(image);
                        Log.d("success", "sucess: " + mNews.getContent());



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
            public void onFailure(Call<ResponseNewsDetail> call, Throwable t) {
                dialog.dismiss();
                System.out.println( "ini gagal " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERR", "onFailure: " + t.getMessage());

            }
        });


    }

}
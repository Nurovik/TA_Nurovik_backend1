package com.moblile.blfc.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.moblile.blfc.Model.Pemain;
import com.moblile.blfc.R;
import com.moblile.blfc.Util.Utils;
import com.squareup.picasso.Picasso;

public class ActivityPemainDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemain_detail);

        final Bundle mData = getIntent().getExtras();
        String pemainJSON = mData.getString("datapemain");
        final Pemain mPemain = Utils.getGsonParser().fromJson(pemainJSON, Pemain.class);


        ImageView image;
        TextView nama, fakultas, nopunggung;
        image = (ImageView) findViewById(R.id.image);
        nama = (TextView) findViewById(R.id.nama);
        fakultas = (TextView) findViewById(R.id.fakultas);
        nopunggung = (TextView) findViewById(R.id.nopunggung);

        nama.setText(mPemain.getNama());
        fakultas.setText(mPemain.getFakultas());
        nopunggung.setText(String.valueOf("No Punggung : " + mPemain.getNopunggung()));

        Picasso.with(getApplicationContext()).load(Utils.BaseUrlcms+mPemain.getImage()).into(image);


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

            case R.id.action_mode:
                showSingleChoiceDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final String[] ANIMATION_TYPE = new String[]{
            "News","klasmen",  "Jadwal", "Login"
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

                } else if (selected.equalsIgnoreCase("News")) {
                    Intent in = new Intent(getApplicationContext(), ActivityNews.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(in);
                } else if (selected.equalsIgnoreCase("Jadwal")) {

                    Intent in = new Intent(getApplicationContext(), ActivityJadwal.class);
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
}
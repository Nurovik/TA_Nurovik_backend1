package com.moblile.blfc.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.moblile.blfc.Activity.ActivityEditScore;
import com.moblile.blfc.Activity.ActivityNewsDetail;
import com.moblile.blfc.Activity.MainActivity;
import com.moblile.blfc.Model.Jadwal;
import com.moblile.blfc.Model.Pemain;
import com.moblile.blfc.R;
import com.moblile.blfc.ResponseServer.ResponseServer;
import com.moblile.blfc.Session.SessionManager;
import com.moblile.blfc.StoreApi.Api;
import com.moblile.blfc.Util.ConvertDate;
import com.moblile.blfc.Util.ItemAnimation;
import com.moblile.blfc.Util.Utils;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListJadwalDashboard extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    SessionManager mSession;
    private List<Jadwal> items = new ArrayList<>();
    Api api = new Api();
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;
    private int animation_type = 0;

    public interface OnItemClickListener {
        void onItemClick(View view, Pemain obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListJadwalDashboard(Context context, List<Jadwal> items, int animation_type) {
        this.items = items;
        ctx = context;
        this.animation_type = animation_type;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public TextView namaliga, status, hari, tempat,teama, teamb;
        public Button btnmenu;


        public OriginalViewHolder(View v) {
            super(v);

            namaliga = (TextView) v.findViewById(R.id.tv_namaliga);
            status = (TextView) v.findViewById(R.id.tv_status);
            hari = (TextView) v.findViewById(R.id.tv_hari);
            tempat = (TextView) v.findViewById(R.id.tv_tempat);
            teama = (TextView) v.findViewById(R.id.tv_teama);
            teamb = (TextView) v.findViewById(R.id.tv_teamb);
            btnmenu = (Button) v.findViewById(R.id.menu);
//            score = (TextView) v.findViewById(R.id.tv_score);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_dashboard, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.e("onBindViewHolder", "onBindViewHolder : " + position);
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;
            ConvertDate convert = new ConvertDate();
            final Jadwal n = items.get(position);
            view.namaliga.setText(n.getNamaliga() +  " | Score : "  + n.getGoal1() + "-" + n.getGoal2());
            view.status.setText(n.getStatus());
            view.hari.setText(convert.convertlongtodate(n.getHari()));
            view.tempat.setText(n.getTempat());
            view.teama.setText(n.getTeam1() );
            view.teamb.setText(n.getTeam2());
            mSession = new SessionManager(ctx);
            final String token = mSession.getToken();
            final String user = mSession.getUsername();


             final String[] menu= new String[]{
                    "Editscore", "Delete Jadwal"
            };

            view.btnmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                    builder.setTitle("Menu");
                    builder.setCancelable(false);
                    builder.setSingleChoiceItems(menu, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String selected = menu[i];
                            if (selected.equalsIgnoreCase("Editscore")) {

                                Intent in = new Intent(ctx, ActivityEditScore.class);
                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                final Bundle args = new Bundle();
                                String dataeditscore = Utils.getGsonParser().toJson(n);
                                args.putString("data", dataeditscore);
                                in.putExtras(args);
                                ctx.startActivity(in);



                            } else if (selected.equalsIgnoreCase("Delete Jadwal")) {


                                //Alert Dialog
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ctx);
                                builder.setTitle("Konfirmasi");
                                builder.setMessage("Apakah Ingin Mendelete Jadwal ?");
                                builder.setIcon(R.drawable.ic_undraw_questions_re_1fy7);
                                builder.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        // Do nothing but close the dialog
                                        dialog.dismiss();
                                    }
                                });

                                builder.setNegativeButton("Ya", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    final  int id = n.getId();
                                        // Do nothing
                                        delete(token,user,String.valueOf(id));
                                        dialog.dismiss();
                                    }
                                });

                                android.app.AlertDialog alert = builder.create();
                                alert.show();




                            }

                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();

                }
            });



            setAnimation(view.itemView, position);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }




    public void delete(String token, String user, String id){
        Log.d("TOKEN", "getData: "+ token);
        final Dialog dialog = new Dialog(ctx);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.view_loading_dialog);
        dialog.show();


        api.Deletejadwal( user, token, id, new Callback<ResponseServer>() {
            @Override
            public void onResponse(Call<ResponseServer> call, Response<ResponseServer> response) {
                dialog.dismiss();
                String message, statusCode;

                if(response.code() != 200){
                    message = "Server Not Respond";
                    Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();


                }else{
                    //berhasil delete data
                    Toast.makeText(ctx, "Data Berhasil Di delete", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ctx, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);

                }


            }

            @Override
            public void onFailure(Call<ResponseServer> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ctx, "Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERR", "onFailure: " + t.getMessage());

            }
        });

    }

}
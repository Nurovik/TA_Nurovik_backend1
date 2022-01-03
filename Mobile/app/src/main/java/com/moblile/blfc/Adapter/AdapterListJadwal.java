package com.moblile.blfc.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moblile.blfc.Model.Jadwal;
import com.moblile.blfc.Model.Pemain;
import com.moblile.blfc.R;
import com.moblile.blfc.Util.ConvertDate;
import com.moblile.blfc.Util.ItemAnimation;
import com.moblile.blfc.Util.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterListJadwal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Jadwal> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;
    private int animation_type = 0;

    public interface OnItemClickListener {
        void onItemClick(View view, Pemain obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListJadwal(Context context, List<Jadwal> items, int animation_type) {
        this.items = items;
        ctx = context;
        this.animation_type = animation_type;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public TextView namaliga, status, hari, tempat,teama, teamb;


        public OriginalViewHolder(View v) {
            super(v);

            namaliga = (TextView) v.findViewById(R.id.tv_namaliga);
            status = (TextView) v.findViewById(R.id.tv_status);
            hari = (TextView) v.findViewById(R.id.tv_hari);
            tempat = (TextView) v.findViewById(R.id.tv_tempat);
            teama = (TextView) v.findViewById(R.id.tv_teama);
            teamb = (TextView) v.findViewById(R.id.tv_teamb);
//            score = (TextView) v.findViewById(R.id.tv_score);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
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
            Jadwal n = items.get(position);
            view.namaliga.setText(n.getNamaliga());
            view.status.setText(n.getStatus());
            view.hari.setText(convert.convertlongtodate(n.getHari()));
            view.tempat.setText(n.getTempat());
            view.teama.setText(n.getTeam1() );
            view.teamb.setText(n.getTeam2());



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

}
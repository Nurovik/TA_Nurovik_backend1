package com.moblile.blfc.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moblile.blfc.Model.Klasmen;
import com.moblile.blfc.Model.News;
import com.moblile.blfc.R;
import com.moblile.blfc.Util.ItemAnimation;
import com.moblile.blfc.Util.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterListKlasmen extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Klasmen> items = new ArrayList<>();

    private Context ctx;
    private AdapterListKlasmen.OnItemClickListener mOnItemClickListener;
    private int animation_type = 0;

    public interface OnItemClickListener {
        void onItemClick(View view, Klasmen obj, int position);
    }

    public void setOnItemClickListener(final AdapterListKlasmen.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListKlasmen(Context context, List<Klasmen> items, int animation_type) {
        this.items = items;
        ctx = context;
        this.animation_type = animation_type;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView keterangan, liga;
//        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            keterangan = (TextView) v.findViewById(R.id.keterangan);
            liga = (TextView) v.findViewById(R.id.liga);

//            preview = (TextView) v.findViewById(R.id.description);
//            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_klasmen, parent, false);
        vh = new AdapterListKlasmen.OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.e("onBindViewHolder", "onBindViewHolder : " + position);
        if (holder instanceof AdapterListKlasmen.OriginalViewHolder) {
            AdapterListKlasmen.OriginalViewHolder view = (AdapterListKlasmen.OriginalViewHolder) holder;

            Klasmen n = items.get(position);
            view.keterangan.setText(n.getDetail());
            view.liga.setText(n.getNamaliga());
            Picasso.with(ctx).load(Utils.BaseUrlcms+n.getGambar()).into(view.image);

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

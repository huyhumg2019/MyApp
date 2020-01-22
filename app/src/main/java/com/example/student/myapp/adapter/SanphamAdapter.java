package com.example.student.myapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.myapp.R;
import com.example.student.myapp.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
    Context context;
    ArrayList<Sanpham> arraysanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sanphammoinhat,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Sanpham sanpham = arraysanpham.get(position);
        holder.txttensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText(decimalFormat.format(sanpham.getGiasanpham()));
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.giamgia).
                error(R.drawable.ic_launcher_background).into(holder.imghinhsanpham);
    }


    @Override
    public int getItemCount() {
        return arraysanpham.size();

    }

    public class ItemHolder extends RecyclerView.ViewHolder{
            public ImageView imghinhsanpham;
            public TextView txttensanpham,txtgiasanpham;

        public ItemHolder(View itemView) {
            super(itemView);
            imghinhsanpham = (ImageView) itemView.findViewById(R.id.imageviewsp);
            txttensanpham = (TextView) itemView.findViewById(R.id.tensp);
            txtgiasanpham = (TextView) itemView.findViewById(R.id.giasp);
        }
    }
}

package com.example.utsandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterMobil extends RecyclerView.Adapter<RecyclerAdapterMobil.ViewHolder> {
    private ArrayList<mobil> mobilArrayList;
    private Context context;
    private ItemClickListener itemClickListener;

    public RecyclerAdapterMobil(Context context, ArrayList<mobil> mobilArrayList) {
        this.mobilArrayList = mobilArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int
            i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_mobil, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.nama_mobil.setText(mobilArrayList.get(i).getNama());

        viewHolder.foto_mobil.setImageResource(Integer.parseInt(mobilArrayList.get(i).getFoto()));

    }

    @Override
    public int getItemCount() {
        return mobilArrayList != null ? mobilArrayList.size() : 0;
    }
    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama_mobil ;
        ImageView foto_mobil;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama_mobil = itemView.findViewById(R.id.nama_mobil);
            foto_mobil = itemView.findViewById(R.id.foto_mobil);



        @Override
        public void onClick(View v) {
            if(itemClickListener!=null) itemClickListener.onClick(v,getAdapterPosition());
        }
        }

    }
}



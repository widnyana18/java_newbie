package com.example.utszodiakmu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterZodiak extends RecyclerView.Adapter<RecyclerAdapterZodiak.ViewHolder>{
    private ArrayList<Zodiak> zodiakArrayList;
    private Context context;
    private ItemClickListener itemClickListener;

    public RecyclerAdapterZodiak(Context context,ArrayList<Zodiak> zodiakArrayList) {
        this.zodiakArrayList = zodiakArrayList;
        this.context = context;
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(
            viewGroup.getContext()).
            inflate(R.layout.list_team, viewGroup,false);
            ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtName.setText(zodiakArrayList.get(i).getName());
        viewHolder.txtKelahiran.setText(zodiakArrayList.get(i).getKelahiran());
        viewHolder.imgLogo.setImageResource(Integer.parseInt(zodiakArrayList.get(i).getPhoto()));
    }

    @Override
    public int getItemCount() {
        return zodiakArrayList!=null ? zodiakArrayList.size():0;
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtKelahiran; ImageView imgLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName=itemView.findViewById(R.id.name_zodiak);
            txtKelahiran =itemView.findViewById(R.id.kelahiran);
            imgLogo=itemView.findViewById(R.id.img_zodiak);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(itemClickListener!=null) itemClickListener.onClick(v,getAdapterPosition());
        }
    }
}

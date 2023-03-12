package com.example.latihanfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterMahasiswa extends
RecyclerView.Adapter<RecyclerAdapterMahasiswa.ViewHolder>{
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    private ItemClickListener itemClickListener;

    public RecyclerAdapterMahasiswa(ArrayList<Mahasiswa> mahasiswaArrayList) {
        this.mahasiswaArrayList = mahasiswaArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.list_mahasiswa,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int no=(i+1); viewHolder.txtNo.setText(String.valueOf(no));
        viewHolder.txtNimm.setText(mahasiswaArrayList.get(i).getNimm());
        viewHolder.txtNamaa.setText(mahasiswaArrayList.get(i).getNamaa());
        viewHolder.txtProdi.setText(mahasiswaArrayList.get(i).getProdi());
    }

    @Override
    public int getItemCount() {
        return mahasiswaArrayList!=null ? mahasiswaArrayList.size():0;
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }


    public	class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener
    {

        TextView txtNo, txtNimm, txtNamaa, txtProdi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNo=itemView.findViewById(R.id.text_no);
            txtNimm=itemView.findViewById(R.id.text_nimm);
            txtNamaa=itemView.findViewById(R.id.text_namaa);
            txtProdi=itemView.findViewById(R.id.text_prodi);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener!=null)
            itemClickListener.onClick(v,getAdapterPosition());
        }

    }
}



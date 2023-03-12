package com.example.utsandroid;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewMobil;
    private RecyclerAdapterMobil recyclerAdapterMobil;
    private ArrayList<mobil> mobilArrayList;
    private ItemClickListener itemClickListener;

    public HomeFragment() {
// Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewMobil=view.findViewById(R.id.rcv_mobil);

        getMobil();

        recyclerAdapterMobil= new RecyclerAdapterMobil(getContext(),mobilArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewMobil.setLayoutManager(layoutManager);
        recyclerViewMobil.setAdapter(recyclerAdapterMobil);

        recyclerAdapterMobil.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                Toast.makeText(getActivity(), mobilArrayList.get(position).getNama(), Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(getActivity(), detailmobil.class);
                intent.putExtra("hero", mobilArrayList.get(position).getFoto());
                intent.putExtra("deskripsi", mobilArrayList.get(position).getHarga());
                intent.putExtra("descr", mobilArrayList.get(position).getDp());
                intent.putExtra("deskri", mobilArrayList.get(position).getAngsuran());
                intent.putExtra("desk", mobilArrayList.get(position).getTenor());
                startActivity(intent);
            }

            @Override
            public void onClick(View view, int position) {

            }

            {


            }
        });

        return	view;
    }


    private void getMobil()
    {
        if(mobilArrayList==null)
        {
            Resources resources = getResources();
            String[] teamName= resources.getStringArray(R.array.nama_mobil);
            final TypedArray teamLogo=resources.obtainTypedArray(R.array.foto_mobil);
            String[] deskripsi = resources.getStringArray(R.array.harga_mobil);
            String[] desc = resources.getStringArray(R.array.dp_mobil);
            String[] deskrip = resources.getStringArray(R.array.angsuran_mobil);
            String[] desk = resources.getStringArray(R.array.tenor_mobil);

            mobilArrayList=new ArrayList<mobil>();
            for (int i=0;i<teamName.length;i++) {
                mobilArrayList.add(new mobil(teamName[i],String.valueOf(teamLogo.getResourceId(i,-1)), deskripsi[i],desc[i],deskrip[i],desk[i]));
            }
        }
    }

}

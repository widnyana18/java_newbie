package com.example.utszodiakmu;

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
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewZodiak;
    private RecyclerAdapterZodiak recyclerAdapterZodiak;
    private ArrayList<Zodiak> zodiakArrayList;

    public HomeFragment() {
// Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewZodiak=view.findViewById(R.id.rcv_zodiak);

        getZodiak();

        recyclerAdapterZodiak = new RecyclerAdapterZodiak(getContext(), zodiakArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewZodiak.setLayoutManager(layoutManager);
        recyclerViewZodiak.setAdapter(recyclerAdapterZodiak);

        recyclerAdapterZodiak.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(getActivity(), zodiakArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();

                Intent intent =  new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("photo", zodiakArrayList.get(position).getPhoto());
                intent.putExtra("name", zodiakArrayList.get(position).getName());
                intent.putExtra("deskripsi", zodiakArrayList.get(position).getDeskripsi());
                startActivity(intent);
            }
        });
        return	view;
    }


    private void getZodiak()
    {
        if(zodiakArrayList==null)
        {
            Resources resources = getResources();
            final TypedArray zodiakLogo=resources.obtainTypedArray(R.array.zodiak_image);
            String[] zodiakName = resources.getStringArray(R.array.zodiak_name);
            String[] kelahiran = resources.getStringArray(R.array.kelahiran);
            String[] deskripsi = resources.getStringArray(R.array.deskripsi);

            zodiakArrayList=new ArrayList<Zodiak>();
            for (int i=0;i<zodiakName.length;i++) {
                zodiakArrayList.add(new Zodiak(zodiakName[i], String.valueOf(zodiakLogo.getResourceId(i,-1)), kelahiran[i], deskripsi[i]));
            }
        }
    }

}


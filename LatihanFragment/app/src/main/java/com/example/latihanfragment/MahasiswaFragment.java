package com.example.latihanfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaFragment extends Fragment {

    private RecyclerView recyclerViewMahasiswa;
    private RecyclerAdapterMahasiswa recyclerAdapterMahasiswa;
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    private DatabaseHandler databaseHandler;
    private SwipeRefreshLayout doRefresh;

    public MahasiswaFragment() {
// Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mahasiswa, container,
                false);

        recyclerViewMahasiswa = view.findViewById(R.id.rcv_mahasiswa);
        doRefresh = view.findViewById(R.id.swipe_refresh);
        doRefresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        getMahasiswa();

        doRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                doRefresh.setRefreshing(false);
            }
        });
        return view;
    }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_add:
                    Toast.makeText(getContext(), "Edit", Toast.LENGTH_SHORT).show();
                    Intent intentAdd = new
                            Intent(getActivity(), AddMahasiswaActivity.class);
                    startActivity(intentAdd);
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    public void openMenuEdit(View v,final int position){
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.menu.menu_edit, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_edit:
                        Intent intentEdit =	new Intent(getActivity(),EditMahasiswaActivity.class);
                        intentEdit.putExtra("ID",mahasiswaArrayList.get(position).getId());
                        intentEdit.putExtra("NIM",mahasiswaArrayList.get(position).getNimm());
                        intentEdit.putExtra("NAMA",mahasiswaArrayList.get(position).getNamaa());

                        Toast.makeText(getContext(),"Edit",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.navigation_delete:
                        Toast.makeText(getContext(),"Delete",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        popup.show();
    }
    @Override
    public void onResume() { super.onResume(); refresh();
    }

    void refresh()
    {
        doRefresh.setRefreshing(true); getMahasiswa();
        try { Thread.sleep(100);
        } catch(InterruptedException e) { System.out.println("got interrupted!");
        }
        doRefresh.setRefreshing(false);
    }

    private void dialogShow(final int posisi)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Yakin ingin menghapus.");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        databaseHandler.delete(String.valueOf(mahasiswaArrayList.get(posisi).getId()));
                        refresh(); dialog.cancel();
                    }
                });

        builder.setNegativeButton(
                "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create(); alert.show();
    }
        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_option, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }

    private void getMahasiswa()
    {
        mahasiswaArrayList= new ArrayList<Mahasiswa>();
        mahasiswaArrayList.add(new Mahasiswa(1,"170010200","Putu Ciko","SI"));
        mahasiswaArrayList.add(new Mahasiswa(1,"170010212","Made Cetar","TI"));
        recyclerAdapterMahasiswa = new RecyclerAdapterMahasiswa(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewMahasiswa.setLayoutManager(layoutManager);
        recyclerViewMahasiswa.setAdapter(recyclerAdapterMahasiswa);

        recyclerAdapterMahasiswa.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                openMenuEdit(view,position);
            }
        });
    }
}




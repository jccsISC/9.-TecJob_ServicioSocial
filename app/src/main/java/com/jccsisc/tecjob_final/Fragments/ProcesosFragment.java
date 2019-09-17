package com.jccsisc.tecjob_final.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jccsisc.tecjob_final.Adapters.Proceso_Adapter;
import com.jccsisc.tecjob_final.Objetos_Firebase.Proceso_Modelo;
import com.jccsisc.tecjob_final.R;

import java.util.ArrayList;

public class ProcesosFragment extends Fragment {


    private RecyclerView rcyVw_procesos;

    private ArrayList<Proceso_Modelo> procesos = new ArrayList<>();
    private Proceso_Adapter procesos_adapter;

    public ProcesosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_procesos, container, false);

        //le damos valores al recycler
        rcyVw_procesos  = view.findViewById(R.id.rcyVw_procesos);
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        rcyVw_procesos.setLayoutManager(lim);
        procesos_adapter = new Proceso_Adapter(procesos);
        rcyVw_procesos.setAdapter(procesos_adapter);

        getPostulaciones();

        return view;
    }

    public void getPostulaciones(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference()
                .child("DB_Alumnos/"+ FirebaseAuth.getInstance().getCurrentUser().getUid() + "/postulaciones")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        procesos.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            Proceso_Modelo Proceso_Modelo = postSnapshot.getValue(Proceso_Modelo.class);
                            procesos.add(Proceso_Modelo);
                        }
                        procesos_adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }


}

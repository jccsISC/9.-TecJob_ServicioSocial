package com.jccsisc.tecjob_final.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jccsisc.tecjob_final.Adapters.Empresa_Adapter;
import com.jccsisc.tecjob_final.Objetos_Firebase.OfertasEmpresa;
import com.jccsisc.tecjob_final.R;
import com.jccsisc.tecjob_final.ValidacionUsuario;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private ArrayList<OfertasEmpresa> ofertasEmpresa = new ArrayList<>();
    private Empresa_Adapter empresa_adapter;

    DatabaseReference myRef;
    FirebaseAuth mAuth;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //con esto detecto al usuario actual
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getUid();

        //obtenemos la db de firebase
        obtenerUsario(uid);

        //le damos valores al recycler
        RecyclerView recyclerEmpresas = view.findViewById(R.id.rcyVw_empresas);
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerEmpresas.setLayoutManager(lim);

        empresa_adapter = new Empresa_Adapter(ofertasEmpresa, getActivity(), R.layout.cardview_empresas);

        recyclerEmpresas.setAdapter(empresa_adapter);

        return view;
    }


    //Metodo para obtener el usuario de firebase
    private void obtenerUsario(String uid) {
        myRef = FirebaseDatabase.getInstance().getReference("DB_Alumnos").child(uid).child("carrera");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ofertas(dataSnapshot.getValue().toString());
                //ValidacionUsuario.carreraAlum = dataSnapshot.toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }//fin metodo obtener usuario


    //Metodo para ofertas
    public void ofertas(String carrera){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("DB_Ofertas").child(carrera).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ofertasEmpresa.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                 //   all(postSnapshot.getKey().toString());
                    OfertasEmpresa ofertasEmpresa = postSnapshot.getValue(OfertasEmpresa.class);
                    ofertasEmpresa.setUid_empresa(postSnapshot.getKey().toString());

                    HomeFragment.this.ofertasEmpresa.add(ofertasEmpresa);
                }
                empresa_adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //metodo Toast
    public void all(String msj) {
        Toast toast = Toast.makeText(getContext(), msj, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        toast.show();
    }


}

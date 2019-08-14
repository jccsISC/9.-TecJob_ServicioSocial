package com.jccsisc.tecjob_final.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jccsisc.tecjob_final.Adapters.Favoritos_Adapter;
import com.jccsisc.tecjob_final.Objetos_Firebase.OfertasEmpresa;
import com.jccsisc.tecjob_final.R;

import java.util.ArrayList;


public class FavoritosFragment extends Fragment {

    private ArrayList<OfertasEmpresa> ofertasEmpresa = new ArrayList<>();
    private Favoritos_Adapter favorito_adapter;

    DatabaseReference myRef;
    FirebaseAuth mAuth;

    public FavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //le damos valores al recycler
        RecyclerView recyclerEmpresas = view.findViewById(R.id.rcyVw_empresas);
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerEmpresas.setLayoutManager(lim);
        favorito_adapter = new Favoritos_Adapter(ofertasEmpresa, getActivity(), R.layout.cardview_empresas);
        recyclerEmpresas.setAdapter(favorito_adapter);

        return view;
    }

    //Metodo para obtener el usuario de firebase



//    //Metodo para ofertas
    public void getFavoritos(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("DB_Alumnos/"+ FirebaseAuth.getInstance().getCurrentUser().getUid() + "/favoritos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ofertasEmpresa.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    OfertasEmpresa ofertasEmpresa = postSnapshot.getValue(OfertasEmpresa.class);
                    ofertasEmpresa.setUid_empresa(postSnapshot.getKey().toString());
                    FavoritosFragment.this.ofertasEmpresa.add(ofertasEmpresa);
                }
                favorito_adapter.notifyDataSetChanged();

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

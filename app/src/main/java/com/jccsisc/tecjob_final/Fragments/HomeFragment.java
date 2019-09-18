package com.jccsisc.tecjob_final.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
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
import com.jccsisc.tecjob_final.Adapters.Oferta_Adapter;
import com.jccsisc.tecjob_final.Objetos_Firebase.OfertasEmpresa;
import com.jccsisc.tecjob_final.R;
import com.jccsisc.tecjob_final.VariablesGlobales;
import com.mikelau.views.shimmer.ShimmerRecyclerViewX;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private ArrayList<OfertasEmpresa> ofertasEmpresa = new ArrayList<>();
    private ArrayList<OfertasEmpresa> ofertasEmpresaFavoritos = new ArrayList<>();
    private Oferta_Adapter oferta_adapter;

    DatabaseReference myRef;
    FirebaseAuth mAuth;
    ShimmerRecyclerViewX recyclerEmpresas;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //con esto detecto al usuario actual
        mAuth = FirebaseAuth.getInstance();
        final String uid = mAuth.getUid();


        //le damos valores al recycler
        recyclerEmpresas = view.findViewById(R.id.rcyVw_empresas);
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerEmpresas.setLayoutManager(lim);

        oferta_adapter = new Oferta_Adapter(ofertasEmpresa, getActivity(), R.layout.cardview_empresas);

        recyclerEmpresas.setAdapter(oferta_adapter);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //obtenemos la db de firebase
                obtenerUsuario(uid);
                oferta_adapter.showShimmer=false;
                oferta_adapter.notifyDataSetChanged();
            }
        },5000);

        return view;
    }


    //Metodo para obtener el usuario de firebase
    private void obtenerUsuario(String uid) {
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
        VariablesGlobales.carrera = carrera;
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference()
                .child("DB_Alumnos/"+ FirebaseAuth.getInstance().getCurrentUser().getUid() + "/favoritos")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ofertasEmpresa.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    OfertasEmpresa ofertasEmpresa = postSnapshot.getValue(OfertasEmpresa.class);
                    ofertasEmpresa.setUid_empresa(postSnapshot.getKey());
                    ofertasEmpresaFavoritos.add(ofertasEmpresa);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        database.getReference().child("DB_Ofertas").child(carrera).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ofertasEmpresa.clear();
                recyclerEmpresas.showShimmerAdapter();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                 //   all(postSnapshot.getKey().toString());
                    OfertasEmpresa ofertasEmpresa = postSnapshot.getValue(OfertasEmpresa.class);
                    ofertasEmpresa.setUid_empresa(postSnapshot.getKey());
                    ofertasEmpresa.setFavorito(esFavorito(postSnapshot.getKey()));

                    HomeFragment.this.ofertasEmpresa.add(ofertasEmpresa);
                }
                oferta_adapter.notifyDataSetChanged();
                recyclerEmpresas.hideShimmerAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public boolean esFavorito(String empresaId){
        boolean esFavorito = false;
        for (OfertasEmpresa favorito: ofertasEmpresaFavoritos) {
            if (empresaId.equals(favorito.getUid_empresa())) {
                esFavorito = true;
                break;
            }
        }
        return esFavorito;
    }

    //metodo Toast
    public void all(String msj) {
        Toast toast = Toast.makeText(getContext(), msj, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        toast.show();
    }


}

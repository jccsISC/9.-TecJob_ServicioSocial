package com.jccsisc.tecjob_final.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
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
import com.jccsisc.tecjob_final.Adapters.Empresa_Adapter;
import com.jccsisc.tecjob_final.Adapters.Favorito_Adapter;
import com.jccsisc.tecjob_final.Modelos.Empresa_Modelo;
import com.jccsisc.tecjob_final.Objetos_Firebase.Favoritos;
import com.jccsisc.tecjob_final.Objetos_Firebase.OfertasEmpresa;
import com.jccsisc.tecjob_final.R;

import java.util.ArrayList;
import java.util.List;


public class FavoritosFragment extends Fragment {


    public FavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

    return view;
    }

}

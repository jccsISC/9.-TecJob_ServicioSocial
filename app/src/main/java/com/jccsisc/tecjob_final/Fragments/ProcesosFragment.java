package com.jccsisc.tecjob_final.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jccsisc.tecjob_final.Adapters.Empresa_Adapter;
import com.jccsisc.tecjob_final.Adapters.Proceso_Adapter;
import com.jccsisc.tecjob_final.Modelos.Empresa_Modelo;
import com.jccsisc.tecjob_final.Modelos.Proceso_Modelo;
import com.jccsisc.tecjob_final.R;

import java.util.ArrayList;
import java.util.List;

public class ProcesosFragment extends Fragment {


    private List<Proceso_Modelo> proceso_modelos;
    private RecyclerView listProcesos;
    private Proceso_Adapter proceso_adapter;


    public ProcesosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_procesos, container, false);

        //le damos valores al recycler
        listProcesos  = view.findViewById(R.id.rcyVw_procesos);
        LinearLayoutManager lim = new LinearLayoutManager(getContext());
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        listProcesos.setLayoutManager(lim);

        datos();
        inicializarAdaptador();

        return view;
    }

    //generarnos los datos falsos
    public void datos()
    {
        proceso_modelos = new ArrayList<>();
        proceso_modelos.add(new Proceso_Modelo("Sams Club Puerto Vallarta","Demostrador","$240.00"));
        proceso_modelos.add(new Proceso_Modelo("Walmart Galerias","Cajero","$7,000.00"));
        proceso_modelos.add(new Proceso_Modelo("Soriana","Demostrador","$2,500.00"));

    }

    //este metodo inicializa el adaptador
    public void inicializarAdaptador()
    {
        proceso_adapter = new Proceso_Adapter(proceso_modelos);
        listProcesos.setAdapter(proceso_adapter);
    }

}

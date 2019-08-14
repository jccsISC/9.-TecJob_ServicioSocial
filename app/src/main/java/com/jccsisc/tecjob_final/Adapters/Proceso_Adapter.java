package com.jccsisc.tecjob_final.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jccsisc.tecjob_final.Modelos.Empresa_Modelo;
import com.jccsisc.tecjob_final.Modelos.Proceso_Modelo;
import com.jccsisc.tecjob_final.R;

import java.util.List;

public class Proceso_Adapter extends RecyclerView.Adapter<Proceso_Adapter.ProcesoViewHolder>
{

    //creamos una lista de empresas
    List<Proceso_Modelo> proceso_modelos;

    //constructor
    public Proceso_Adapter(List<Proceso_Modelo> proceso_modelos)
    {
        this.proceso_modelos = proceso_modelos;
    }

    //creamos la vista para el recycler
    @NonNull
    @Override
    public ProcesoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_procesos,parent,false);


        return new ProcesoViewHolder(v);
    }


    //aqui asignamos los valores a las vistas
    @Override
    public void onBindViewHolder(@NonNull ProcesoViewHolder procesoviewHolder, int position) {

        //optenemos la posicion de la lista
        Proceso_Modelo proceso_modelo = proceso_modelos.get(position);

        procesoviewHolder.nomEmpresa.setText(proceso_modelo.getNombre());
        procesoviewHolder.nomVacante.setText(proceso_modelo.getVacante());
    }

    //cremos el tamaño del recycler
    @Override
    public int getItemCount() {
        return proceso_modelos.size();
    }

    //Primer clase que tenemos q creamos nuestra innerclas
    public class ProcesoViewHolder extends RecyclerView.ViewHolder
    {
        private TextView nomEmpresa;
        private TextView nomVacante;

        //constructor de nuestra clase

        public ProcesoViewHolder(View itemView) {
            super(itemView);

            this.nomEmpresa = itemView.findViewById(R.id.txtVw_nomEmpresa_CardProcesos);
            this.nomVacante = itemView.findViewById(R.id.txtVw_nomVacante_proceso);
        }
    }

}//FIN DE LA CLASE
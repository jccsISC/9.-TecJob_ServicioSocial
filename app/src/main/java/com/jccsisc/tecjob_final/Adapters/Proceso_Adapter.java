package com.jccsisc.tecjob_final.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jccsisc.tecjob_final.Modelos.Proceso_Modelo;
import com.jccsisc.tecjob_final.R;
import com.squareup.picasso.Picasso;

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

        procesoviewHolder.nomEmpresa.setText(proceso_modelo.getNombre_empresa());
        procesoviewHolder.nomVacante.setText(proceso_modelo.getNombre_puesto());
        Picasso.get().load(proceso_modelo.getFoto()).into(procesoviewHolder.imageView_EmpresaProcesos);

        if(proceso_modelo.getStatus().equals("postulado")){
            procesoviewHolder.imageView_Aplicado.setImageResource(R.drawable.check_proceso);
            procesoviewHolder.imageView_Visto.setImageResource(R.drawable.verified);
            procesoviewHolder.imageView_EnProceso.setImageResource(R.drawable.verified);
        }else if(proceso_modelo.getStatus().equals("en revision")){
            procesoviewHolder.imageView_Aplicado.setImageResource(R.drawable.verified);
            procesoviewHolder.imageView_Visto.setImageResource(R.drawable.check_proceso);
            procesoviewHolder.imageView_EnProceso.setImageResource(R.drawable.verified);
        }else if(proceso_modelo.getStatus().equals("aceptado")){
            procesoviewHolder.txt_aceptado_cancelado.setText("Aceptado");
            procesoviewHolder.imageView_Aplicado.setImageResource(R.drawable.verified);
            procesoviewHolder.imageView_Visto.setImageResource(R.drawable.verified);
            procesoviewHolder.imageView_EnProceso.setImageResource(R.drawable.ic_activo);
        }
        else if(proceso_modelo.getStatus().equals("cancelado")){
            procesoviewHolder.txt_aceptado_cancelado.setText("Cancelado");
            procesoviewHolder.imageView_Aplicado.setImageResource(R.drawable.verified);
            procesoviewHolder.imageView_Visto.setImageResource(R.drawable.verified);
            procesoviewHolder.imageView_EnProceso.setImageResource(R.drawable.ic_cancelada);
        }

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
        private TextView nomVacante,txt_aceptado_cancelado;
         ImageView imageView_EmpresaProcesos,imageView_Aplicado, imageView_Visto, imageView_EnProceso;

        //constructor de nuestra clase

        public ProcesoViewHolder(View itemView) {
            super(itemView);
            this.txt_aceptado_cancelado = itemView.findViewById(R.id.txt_aceptado_cancelado);
            this.imageView_EmpresaProcesos = itemView.findViewById(R.id.imageView_EmpresaProcesos);
            this.nomEmpresa = itemView.findViewById(R.id.txtVw_nomEmpresa_CardProcesos);
            this.nomVacante = itemView.findViewById(R.id.txtVw_nomVacante_proceso);
            this.imageView_Aplicado = itemView.findViewById(R.id.imageView_Aplicado);
            this.imageView_Visto = itemView.findViewById(R.id.imageView_Visto);
            this.imageView_EnProceso = itemView.findViewById(R.id.imageView_EnProceso);
        }
    }

}//FIN DE LA CLASE
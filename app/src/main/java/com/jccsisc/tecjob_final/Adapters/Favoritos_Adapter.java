package com.jccsisc.tecjob_final.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jccsisc.tecjob_final.Activities.DetalleVacanteActivity;
import com.jccsisc.tecjob_final.Objetos_Firebase.Proceso_Modelo;
import com.jccsisc.tecjob_final.Objetos_Firebase.ModeloAlumno;
import com.jccsisc.tecjob_final.Objetos_Firebase.OfertasEmpresa;
import com.jccsisc.tecjob_final.R;
import com.jccsisc.tecjob_final.VariablesGlobales;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Favoritos_Adapter extends RecyclerView.Adapter<Favoritos_Adapter.OfertasViewHolder>
{
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    //creamos una lista de empresas
    List<OfertasEmpresa> ofertas_Modelo;
    private int resource;
    private Activity activity;

    //constructor
    public Favoritos_Adapter(List<OfertasEmpresa> empresa_modelos, Activity activity, int resource)
    {
        this.ofertas_Modelo = empresa_modelos;
        this.activity = activity;
        this.resource = resource;
    }

    //creamos la vista para el recycler
    @NonNull
    @Override
    public OfertasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_favoritos,parent,false);

        OfertasViewHolder holder = new OfertasViewHolder(v);
        return holder;
    }


    //aqui asignamos los valores a las vistas
    @Override
    public void onBindViewHolder(@NonNull final OfertasViewHolder ofertasViewHolder, int position) {
        //optenemos la posicion de la lista
        final OfertasEmpresa empresa_modelo = ofertas_Modelo.get(position);
//        final OfertasEmpresa ofertasEmpresa = ofertas_Modelo.get(position);

        ofertasViewHolder.nomEmpresa.setText(empresa_modelo.getEmpresa());
        ofertasViewHolder.nomVacante.setText(empresa_modelo.getNombre_puesto());
        ofertasViewHolder.horaPublicada.setText(empresa_modelo.getFecha_publicada());
        ofertasViewHolder.turnoVacante.setText(empresa_modelo.getTurno());
        // ofertasViewHolder.img_empresa.setImageResource(R.drawable.sams);
        //con esto detecto al usuario actual
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getUid();

        myRef = FirebaseDatabase.getInstance().getReference();

//        int index = ofertasViewHolder.getAdapterPosition();
//        String id = ofertas_Modelo.get(index).getUid_empresa();

        Picasso.get().load(empresa_modelo.getFoto())
                .into(ofertasViewHolder.img_empresa);


//        ofertasViewHolder.cardViewEmpresa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                VariablesGlobales.empresa = empresa_modelo.getEmpresa();
////                VariablesGlobales.nombre_puesto= empresa_modelo.getNombre_puesto();
////                VariablesGlobales.turno = empresa_modelo.getTurno();
////                VariablesGlobales.razon_social = empresa_modelo.getRazon_social();
////                VariablesGlobales.contacto = empresa_modelo.getContacto();
////                VariablesGlobales.domicilio = empresa_modelo.getDomicilio();
////                VariablesGlobales.descripcion_puesto = empresa_modelo.getDesc_puesto();
////                VariablesGlobales.habilidades = empresa_modelo.getHabilidades();
////                VariablesGlobales.requisitos = empresa_modelo.getRequisitos();
////                VariablesGlobales.salario_mensual = empresa_modelo.getSalario_mensual();
////                VariablesGlobales.uid_oferta = empresa_modelo.getUid_oferta();
////                VariablesGlobales.foto = empresa_modelo.getFoto();
////                alt(VariablesGlobales.habilidades);
//////                Intent intent = new Intent(activity, DetalleVacanteActivity.class);
//////
//////                activity.startActivity(intent);
//
//                VariablesGlobales.uid_oferta = empresa_modelo.getUid_oferta();
//
////                VariablesGlobales.foto = empresa_modelo.getFoto();
//
//                Intent intent = new Intent(activity, DetalleVacanteActivity.class);
//
//                activity.startActivity(intent);
//
//            }
//        });


        ofertasViewHolder.check_favorito.setSelected(true);

        ofertasViewHolder.check_favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("DB_Alumnos/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/favoritos/"
                        + empresa_modelo.getUid_empresa()).removeValue();
                Toast.makeText(view.getContext(),"Borrado de favoritos",Toast.LENGTH_SHORT).show();
            }
        });


        ofertasViewHolder.btn_postularse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Proceso_Modelo postulaciones = new Proceso_Modelo();


                postulaciones.postularme(empresa_modelo.getFoto(), empresa_modelo.getNombre_puesto(),"postulado", empresa_modelo.getUid_oferta(), empresa_modelo.getEmpresa());
                VariablesGlobales.uid_oferta = empresa_modelo.getUid_oferta();

                myRef.child("DB_Alumnos").child(FirebaseAuth.getInstance().getUid())
                        .child("postulaciones").child(empresa_modelo.getUid_empresa()).setValue(postulaciones).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        ModeloAlumno modeloAlumno = new ModeloAlumno();
                        modeloAlumno.postulado(FirebaseAuth.getInstance().getUid(),"Postulado");

                        myRef.child("DB_Ofertas").child(VariablesGlobales.carrera).child(empresa_modelo.getUid_oferta()).child("postulados").child(FirebaseAuth.getInstance().getUid()).setValue(modeloAlumno);
                    }
                });


                Snackbar.make(view,"Postulado",Snackbar.LENGTH_SHORT).show();




            }
        });

    }

    public void alt(String mensaje){
        Toast.makeText(activity,mensaje,Toast.LENGTH_SHORT).show();
    }

    //cremos el tamaño del recycler
    @Override
    public int getItemCount() {

        if(ofertas_Modelo.size()>0)
        {
            return ofertas_Modelo.size();
        }

        return 0;
    }

    //Primer clase que tenemos q creamos nuestra innerclas
    public static class OfertasViewHolder extends RecyclerView.ViewHolder
    {


        private TextView nomEmpresa;
        private TextView nomVacante;
        private TextView horaPublicada;
        private TextView turnoVacante;
//        private CardView cardViewEmpresa;
        public Button btn_postularse;
        private ImageView img_empresa;
        private CheckBox check_favorito;

        //constructor de nuestra clase

        public OfertasViewHolder(View itemView) {
            super(itemView);

            this.nomEmpresa  = itemView.findViewById(R.id.txtVw_nomEmpresa);
            this.nomVacante  = itemView.findViewById(R.id.txtVw_nomVacante);
            this.nomVacante.setSelected(true);
            this.horaPublicada = itemView.findViewById(R.id.txtVw_timePublicado);
            this.turnoVacante  = itemView.findViewById(R.id.txtVw_horarioEmpresa);
            this.turnoVacante.setSelected(true);
//            this.cardViewEmpresa=itemView.findViewById(R.id.cardView_imgEmpresa_Favoritos);
            this.btn_postularse= itemView.findViewById(R.id.btnVw_GuardarOferta);
            this.img_empresa = itemView.findViewById(R.id.imgVw_empresaCard);

            this.check_favorito = itemView.findViewById(R.id.checkbox_Favorito);

        }

    }


}//FIN DE LA CLASE
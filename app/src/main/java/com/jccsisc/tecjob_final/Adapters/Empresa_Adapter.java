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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jccsisc.tecjob_final.Activities.DetalleVacanteActivity;
import com.jccsisc.tecjob_final.Modelos.Proceso_Modelo;
import com.jccsisc.tecjob_final.Objetos_Firebase.ModeloAlumno;
import com.jccsisc.tecjob_final.Objetos_Firebase.OfertasEmpresa;
import com.jccsisc.tecjob_final.R;
import com.jccsisc.tecjob_final.VariablesGlobales;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Empresa_Adapter extends RecyclerView.Adapter<Empresa_Adapter.OfertasViewHolder>
{
    private DatabaseReference myRef;
    FirebaseAuth mAuth;

    //creamos una lista de empresas
    List<OfertasEmpresa> ofertas_Modelo;
    private int resource;
    private Activity activity;

    //constructor
    public Empresa_Adapter(List<OfertasEmpresa> empresa_modelos, Activity activity, int resource)
    {
        this.ofertas_Modelo = empresa_modelos;
        this.activity = activity;
        this.resource = resource;
    }

    //creamos la vista para el recycler
    @NonNull
    @Override
    public OfertasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_empresas,parent,false);

        OfertasViewHolder holder = new OfertasViewHolder(v);



        return holder;
    }


    //aqui asignamos los valores a las vistas
    @Override
    public void onBindViewHolder(@NonNull final OfertasViewHolder ofertasViewHolder, int position) {

        //optenemos la posicion de la lista
        final OfertasEmpresa empresa_modelo = ofertas_Modelo.get(position);
        final OfertasEmpresa ofertasEmpresa = ofertas_Modelo.get(position);

        ofertasViewHolder.nomEmpresa.setText(empresa_modelo.getEmpresa());
        ofertasViewHolder.nomVacante.setText(empresa_modelo.getNombre_puesto());
        ofertasViewHolder.horaPublicada.setText(empresa_modelo.getFecha_publicada());
        ofertasViewHolder.turnoVacante.setText(empresa_modelo.getTurno());
       // ofertasViewHolder.img_empresa.setImageResource(R.drawable.sams);
        //con esto detecto al usuario actual
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getUid();
        //obtenemos la db de firebase

        myRef = FirebaseDatabase.getInstance().getReference();
        int index = ofertasViewHolder.getAdapterPosition();
        final String id = ofertas_Modelo.get(index).getUid_empresa();


        Picasso.get().load(empresa_modelo.getFoto())
                .into(ofertasViewHolder.img_empresa);

        ofertasViewHolder.cardViewEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VariablesGlobales.empresa = empresa_modelo.getEmpresa();
                VariablesGlobales.nombre_puesto= empresa_modelo.getNombre_puesto();
                VariablesGlobales.turno = empresa_modelo.getTurno();
                VariablesGlobales.razon_social = empresa_modelo.getRazon_social();
                VariablesGlobales.contacto = empresa_modelo.getContacto();
                VariablesGlobales.domicilio = empresa_modelo.getDomicilio();
                VariablesGlobales.descripcion_puesto = empresa_modelo.getDesc_puesto();
                VariablesGlobales.habilidades = empresa_modelo.getHabilidades();
                VariablesGlobales.requisitos = empresa_modelo.getRequisitos();
                VariablesGlobales.salario_mensual = empresa_modelo.getSalario_mensual();
                VariablesGlobales.uid_oferta = empresa_modelo.getUid_oferta();
                VariablesGlobales.foto = empresa_modelo.getFoto();
                Intent intent = new Intent(activity, DetalleVacanteActivity.class);

                activity.startActivity(intent);

            }
        });


        ofertasViewHolder.check_favorito.setSelected(true);
        ofertasViewHolder.check_favorito.setChecked(empresa_modelo.getFavorito());

        ofertasViewHolder.check_favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChk = ((CheckBox)view).isChecked();

                if(isChk==true)
                {

                    String fecha_publicada,nombre_puesto,turno, empresa, uid_empresa, foto;
                    foto    = empresa_modelo.getFoto();
                    empresa = empresa_modelo.getEmpresa();
                    fecha_publicada = empresa_modelo.getFecha_publicada();
                    nombre_puesto = empresa_modelo.getNombre_puesto();
                    turno = empresa_modelo.getTurno();
                    uid_empresa = empresa_modelo.getUid_empresa();

                    String id = mAuth.getUid();//con este le decimos a donde guarde

                    OfertasEmpresa favorito = new OfertasEmpresa(foto,fecha_publicada,nombre_puesto,turno,empresa);

                    myRef.child("DB_Alumnos").child(id).child("favoritos").child(uid_empresa).setValue(favorito);
                    //  all("Guardado");*/


                    Snackbar.make(view,"Agregado a Favoritos",Snackbar.LENGTH_SHORT).show();

                }

                if(isChk==false)
                {

                    String fecha_publicada,nombre_puesto,turno, empresa, uid_empresa, foto;
                    foto    = empresa_modelo.getFoto();
                    empresa = empresa_modelo.getEmpresa();
                    nombre_puesto = empresa_modelo.getNombre_puesto();
                    fecha_publicada = empresa_modelo.getFecha_publicada();
                    turno = empresa_modelo.getTurno();
                    uid_empresa = empresa_modelo.getUid_empresa();

                    String id = mAuth.getUid();//con este le decimos a donde guarde

                    OfertasEmpresa favorito = new OfertasEmpresa(foto,fecha_publicada,nombre_puesto,turno,empresa);

                    myRef.child("DB_Alumnos").child(id).child("favoritos").child(uid_empresa).removeValue();
                    //  all("Guardado");*/


                    Snackbar.make(view,"Removido de Favoritos",Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        ofertasViewHolder.btn_postularse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isbtnCheck = ((Button)view).isEnabled();


                if(isbtnCheck ==true)
                {


                    Proceso_Modelo postulaciones = new Proceso_Modelo();
                    postulaciones.postularme(empresa_modelo.getFoto(), empresa_modelo.getNombre_puesto()
                                              ,"postulado", empresa_modelo.getUid_oferta(), empresa_modelo.getEmpresa());

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


                }else
                {

                }


            }
        });

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
        private CardView cardViewEmpresa;
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
            this.cardViewEmpresa=itemView.findViewById(R.id.cardView_imgEmpresa);
            this.btn_postularse= itemView.findViewById(R.id.btnVw_GuardarOferta);
            this.img_empresa = itemView.findViewById(R.id.imgVw_empresaCard);

            this.check_favorito = itemView.findViewById(R.id.checkbox_Favorito);

        }

    }


}//FIN DE LA CLASE
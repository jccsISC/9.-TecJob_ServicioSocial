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

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jccsisc.tecjob_final.Activities.DetalleVacanteActivity;
import com.jccsisc.tecjob_final.Objetos_Firebase.OfertasEmpresa;
import com.jccsisc.tecjob_final.R;

import java.util.List;

public class Favoritos_Adapter extends RecyclerView.Adapter<Favoritos_Adapter.OfertasViewHolder>
{
    private DatabaseReference myRef;
    private StorageReference myStorage;
    FirebaseAuth mAuth;

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
//        OfertasEmpresa ofertasEmpresa = ofertas_Modelo.get(position);

        ofertasViewHolder.nomEmpresa.setText(empresa_modelo.getEmpresa());
        ofertasViewHolder.nomVacante.setText(empresa_modelo.getNombre_puesto());
        ofertasViewHolder.horaPublicada.setText(empresa_modelo.getFecha_publicada());
        ofertasViewHolder.turnoVacante.setText(empresa_modelo.getTurno());
        ofertasViewHolder.img_empresa.setImageResource(R.drawable.sams);


//        ofertasViewHolder.check_favorito.setChecked(true);
        //obtenemos la db de firebase
        myRef = FirebaseDatabase.getInstance().getReference();
        myStorage = FirebaseStorage.getInstance().getReference();

        ofertasViewHolder.cardViewEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int index = ofertasViewHolder.getAdapterPosition();
                String id = ofertas_Modelo.get(index).getUid_empresa();

                Intent intent = new Intent(activity, DetalleVacanteActivity.class);
                intent.putExtra("llave", id);
                activity.startActivity(intent);

            }
        });


        ofertasViewHolder.check_favorito.setSelected(true);

        ofertasViewHolder.check_favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("DB_Alumnos/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/favoritos/" + empresa_modelo.getUid_empresa()).removeValue();
            }
        });

//        ofertasViewHolder.check_favorito.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean isChk = ((CheckBox)view).isChecked();
//
//                if(isChk == true)
//                {
//                    String fecha_publicada,nombre_puesto,turno, empresa, uid_empresa, foto;
//                    empresa = empresa_modelo.getEmpresa();
//                    fecha_publicada = empresa_modelo.getFecha_publicada();
//                    nombre_puesto = empresa_modelo.getNombre_puesto();
//                    turno = empresa_modelo.getTurno();
//                    uid_empresa = empresa_modelo.getUid_empresa();
//                    mAuth = FirebaseAuth.getInstance();
//                    String id = mAuth.getUid();//con este le decimos a donde guarde
//                    OfertasEmpresa favorito = new OfertasEmpresa(fecha_publicada,nombre_puesto,turno,empresa);
//                    myRef.child("DB_Alumnos").child(id).child("favoritos").child(uid_empresa).setValue(favorito);
//                    Snackbar.make(view,"Agregado a Favoritos",Snackbar.LENGTH_SHORT).show();
//
//                }
//            }
//        });

        ofertasViewHolder.btn_postularse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isbtnCheck = ((Button)view).isEnabled();

                if(isbtnCheck ==true)
                {

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
            this.horaPublicada = itemView.findViewById(R.id.txtVw_timePublicado);
            this.turnoVacante  = itemView.findViewById(R.id.txtVw_horarioEmpresa);
            this.cardViewEmpresa=itemView.findViewById(R.id.cardView_imgEmpresa);
            this.btn_postularse= itemView.findViewById(R.id.btnVw_GuardarOferta);
            this.img_empresa = itemView.findViewById(R.id.imgVw_empresaCard);

            this.check_favorito = itemView.findViewById(R.id.checkbox_Favorito);

        }

    }


}//FIN DE LA CLASE
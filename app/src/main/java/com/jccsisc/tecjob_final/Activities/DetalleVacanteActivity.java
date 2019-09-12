package com.jccsisc.tecjob_final.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jccsisc.tecjob_final.Fragments.HomeFragment;
import com.jccsisc.tecjob_final.Modelos.Proceso_Modelo;
import com.jccsisc.tecjob_final.Objetos_Firebase.InfoEmpresa;
import com.jccsisc.tecjob_final.Objetos_Firebase.ModeloAlumno;
import com.jccsisc.tecjob_final.Objetos_Firebase.OfertasEmpresa;
import com.jccsisc.tecjob_final.R;
import com.jccsisc.tecjob_final.ValidacionUsuario;
import com.jccsisc.tecjob_final.VariablesGlobales;
import com.squareup.picasso.Picasso;

public class DetalleVacanteActivity extends AppCompatActivity {


    private ImageView img_Empresa;
    private TextView txt_nomEmpresa;
    private TextView txt_razonEmpresa;
    private TextView txt_contacto;
    private TextView txt_domicilio;
    private TextView txt_vacante;
    private TextView txt_descripcion;
    private TextView txt_habilidades;
    private TextView txt_requisitos;
    private TextView txt_salario;
    private TextView txt_horario;
    private Button btn_postulate;


    ValidacionUsuario v = new ValidacionUsuario();

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private String foto,nombre_empresa, nombre_puesto, uid_oferta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_vacante);


        mAuth = FirebaseAuth.getInstance();//obtenemos el usuario iniciado

        myRef = FirebaseDatabase.getInstance().getReference();

        //para que aparesca el back button ahora para que funcione nos vamos al manifest
        getSupportActionBar().setTitle("Detalle de la Oferta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        img_Empresa      = findViewById(R.id.imgVw_empresaDetalle);
        txt_nomEmpresa   = findViewById(R.id.txtVw_nomEmpresaDetalle);
        txt_nomEmpresa.setSelected(true);
        txt_razonEmpresa = findViewById(R.id.txtVw_razonSocial);
        txt_contacto     = findViewById(R.id.txtVw_numEmpresaDetalle);
        txt_domicilio    = findViewById(R.id.txtVw_domicilioEmpresa);
        txt_domicilio.setSelected(true);
        txt_vacante      = findViewById(R.id.txtVw_nomVacanteDetalle);
        txt_vacante.setSelected(true);
        txt_descripcion  = findViewById(R.id.txtVw_descripcionVacant);
        txt_habilidades  = findViewById(R.id.txtVw_habilidades);
        txt_requisitos   = findViewById(R.id.txtVw_requisitos);
        txt_salario      = findViewById(R.id.txtVw_salario);
        txt_horario      = findViewById(R.id.txtVw_turnoDetalle);
        btn_postulate    = findViewById(R.id.btnVw_postulateDetalle);



        getData(VariablesGlobales.carrera,VariablesGlobales.uid_oferta);

//        Picasso.get().load(VariablesGlobales.foto).placeholder(R.drawable.ic_procesos).into(img_Empresa);
//        txt_nomEmpresa.setText(VariablesGlobales.empresa);
//        txt_razonEmpresa.setText(VariablesGlobales.razon_social);
//        txt_contacto.setText(VariablesGlobales.contacto);
//        txt_domicilio.setText(VariablesGlobales.domicilio);
//        txt_vacante.setText(VariablesGlobales.nombre_puesto);
//        txt_descripcion.setText(VariablesGlobales.descripcion_puesto);
//        txt_habilidades.setText(VariablesGlobales.habilidades);
//        txt_requisitos.setText(VariablesGlobales.requisitos);
//        txt_salario.setText(VariablesGlobales.salario_mensual);
//        txt_horario.setText(VariablesGlobales.turno);

        btn_postulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean isbtnCheck = ((Button)view).isEnabled();


                if(isbtnCheck ==true)
                {
                    postulate();
                    Snackbar.make(view,"Postulado",Snackbar.LENGTH_SHORT).show();
                }else
                {

                }

            }
        });

    }//fin onCreate

    public void getData(String carreraa, String uid_oferton){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference()
                .child("DB_Ofertas/"+carreraa)
                .orderByChild("uid_oferta")
                .equalTo(uid_oferton)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            OfertasEmpresa ofertasEmpresa = postSnapshot.getValue(OfertasEmpresa.class);

                            Picasso.get().load(ofertasEmpresa.getFoto()).placeholder(R.drawable.ic_procesos).into(img_Empresa);

                            txt_nomEmpresa.setText(ofertasEmpresa.getEmpresa());
                            txt_razonEmpresa.setText(ofertasEmpresa.getRazon_social());
                            txt_contacto.setText(ofertasEmpresa.getContacto());
                            txt_domicilio.setText(ofertasEmpresa.getDomicilio());
                            txt_vacante.setText(ofertasEmpresa.getNombre_puesto());
                            txt_descripcion.setText(ofertasEmpresa.getDesc_puesto());
                            txt_habilidades.setText(ofertasEmpresa.getHabilidades());
                            txt_requisitos.setText(ofertasEmpresa.getRequisitos());
                            txt_salario.setText(ofertasEmpresa.getSalario_mensual());
                            txt_horario.setText(ofertasEmpresa.getTurno());

                            foto = ofertasEmpresa.getFoto();
                            nombre_empresa = ofertasEmpresa.getEmpresa();
                            nombre_puesto = ofertasEmpresa.getNombre_puesto();
                            uid_oferta = ofertasEmpresa.getUid_oferta();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }


    public void alt(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    //metodo para crear una postulacion
    public void postulate()
    {

        //Picasso.get().load(VariablesGlobales.foto).placeholder(R.drawable.ic_procesos).into(img_Empresa);

        Proceso_Modelo postulaciones = new Proceso_Modelo();
        postulaciones.postularme(foto, nombre_puesto
                ,"postulado", uid_oferta, nombre_empresa);

        myRef.child("DB_Alumnos").child(mAuth.getUid())
                .child("postulaciones")
                .child(VariablesGlobales.uid_oferta)
                .setValue(postulaciones).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                ModeloAlumno modeloAlumno = new ModeloAlumno();
                modeloAlumno.postulado(mAuth.getUid(),"Postulado");

                myRef.child("DB_Ofertas").child(VariablesGlobales.carrera).child(VariablesGlobales.uid_oferta).child("postulados").child(mAuth.getUid()).setValue(modeloAlumno);
            }
        });


    }

    public void getUser(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference()
                .child("DB_Alumnos")
                .orderByChild("uid")
                .equalTo(mAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            OfertasEmpresa ofertasEmpresa = postSnapshot.getValue(OfertasEmpresa.class);

                            Picasso.get().load(ofertasEmpresa.getFoto()).placeholder(R.drawable.ic_procesos).into(img_Empresa);
                            txt_nomEmpresa.setText(ofertasEmpresa.getEmpresa());
                            txt_razonEmpresa.setText(ofertasEmpresa.getRazon_social());
                            txt_contacto.setText(ofertasEmpresa.getContacto());
                            txt_domicilio.setText(ofertasEmpresa.getDomicilio());
                            txt_vacante.setText(ofertasEmpresa.getNombre_puesto());
                            txt_descripcion.setText(ofertasEmpresa.getDesc_puesto());
                            txt_habilidades.setText(ofertasEmpresa.getHabilidades());
                            txt_requisitos.setText(ofertasEmpresa.getRequisitos());
                            txt_salario.setText(ofertasEmpresa.getSalario_mensual());
                            txt_horario.setText(ofertasEmpresa.getTurno());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }



}

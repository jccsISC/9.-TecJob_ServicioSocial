package com.jccsisc.tecjob_final.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jccsisc.tecjob_final.Fragments.HomeFragment;
import com.jccsisc.tecjob_final.Objetos_Firebase.InfoEmpresa;
import com.jccsisc.tecjob_final.Objetos_Firebase.OfertasEmpresa;
import com.jccsisc.tecjob_final.R;
import com.jccsisc.tecjob_final.ValidacionUsuario;

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

    InfoEmpresa infoEmpresa;
    DatabaseReference myRef;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_vacante);


        //para que aparesca el back button ahora para que funcione nos vamos al manifest
        getSupportActionBar().setTitle("Detalle de la Oferta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        id = getIntent().getStringExtra("llave");
        myRef = FirebaseDatabase.getInstance().getReference("DB_Ofertas").child(ValidacionUsuario.carreraAlum);
        obtenerEmpresa(id);


        img_Empresa      = findViewById(R.id.imgVw_empresaDetalle);
        txt_nomEmpresa   = findViewById(R.id.txtVw_nomEmpresaDetalle);
        txt_razonEmpresa = findViewById(R.id.txtVw_razonSocial);
        txt_contacto     = findViewById(R.id.txtVw_numEmpresaDetalle);
        txt_domicilio    = findViewById(R.id.txtVw_domicilioEmpresa);
        txt_vacante      = findViewById(R.id.txtVw_nomVacanteDetalle);
        txt_descripcion  = findViewById(R.id.txtVw_descripcionVacant);
        txt_habilidades  = findViewById(R.id.txtVw_habilidades);
        txt_requisitos   = findViewById(R.id.txtVw_requisitos);
        txt_salario      = findViewById(R.id.txtVw_salario);
        txt_horario      = findViewById(R.id.txtVw_turnoDetalle);
        btn_postulate    = findViewById(R.id.btnVw_postulateDetalle);

        btn_postulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Postulado",Toast.LENGTH_SHORT).show();
            }
        });


    }

    //Metodo para obtener el usuario de firebase
    private void obtenerUsario(String uid) {
        myRef = FirebaseDatabase.getInstance().getReference("DB_Alumnos").child(uid).child("carrera");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                obtenerEmpresa(dataSnapshot.getValue().toString());
                ValidacionUsuario.carreraAlum = dataSnapshot.toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }//fin metodo obtener usuario


    public void obtenerEmpresa(String carrera)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference().child("DB_Ofertas").child(carrera).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

//                    OfertasEmpresa ofertasEmpresa = postSnapshot.getValue(OfertasEmpresa.class);
//                    ofertasEmpresa.setUid_empresa(postSnapshot.getKey().toString());


                    infoEmpresa = postSnapshot.getValue(InfoEmpresa.class);
                    txt_nomEmpresa.setText(infoEmpresa.getEmpresa());
                    txt_contacto.setText(infoEmpresa.getContacto());
                    txt_descripcion.setText(infoEmpresa.getDesc_puesto());


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

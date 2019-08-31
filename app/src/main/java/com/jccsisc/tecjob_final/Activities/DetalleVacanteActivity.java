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

import com.google.firebase.auth.FirebaseAuth;
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


    DatabaseReference myRef;
    String id = "";
    String descripcion = "";
    String name = "";
    String puesto = "";
    String sueldo = "";
    String domicilio = "";
    String contacto = "";
    String turno = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_vacante);


        //para que aparesca el back button ahora para que funcione nos vamos al manifest
        getSupportActionBar().setTitle("Detalle de la Oferta");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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


        Picasso.get().load(VariablesGlobales.foto).placeholder(R.drawable.ic_procesos).into(img_Empresa);
        txt_nomEmpresa.setText(VariablesGlobales.empresa);
        txt_razonEmpresa.setText(VariablesGlobales.razon_social);
        txt_contacto.setText(VariablesGlobales.contacto);
        txt_domicilio.setText(VariablesGlobales.domicilio);
        txt_vacante.setText(VariablesGlobales.nombre_puesto);
        txt_descripcion.setText(VariablesGlobales.descripcion_puesto);
        txt_habilidades.setText(VariablesGlobales.habilidades);
        txt_requisitos.setText(VariablesGlobales.requisitos);
        txt_salario.setText(VariablesGlobales.salario_mensual);
        txt_horario.setText(VariablesGlobales.turno);

        btn_postulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Postulado",Toast.LENGTH_SHORT).show();
            }
        });


    }

}

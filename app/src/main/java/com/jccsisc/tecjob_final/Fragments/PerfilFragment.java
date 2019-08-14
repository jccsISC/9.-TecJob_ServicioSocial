package com.jccsisc.tecjob_final.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jccsisc.tecjob_final.R;
import com.jccsisc.tecjob_final.ValidacionUsuario;


public class PerfilFragment extends Fragment {

    private EditText edt_ControlNum,edt_Name,edt_hbd,edt_age,edt_phoneNum,edt_colony,edt_street,edt_experience,edt_skills,edt_horaDisp, edt_nss;
    private Spinner sp_Carrera, sp_Semestre;
    private RadioButton rb_Matutino, rb_Vespertino, rb_Mixto, rb_Si, rb_No;
    private Button btn_Modify;
    //creamos la referencia
    private DatabaseReference myRef;
    FirebaseAuth mAuth;


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        //con esto detecto al usuario actual
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getUid();
        
        //obtenemos la db de firebase
        myRef = FirebaseDatabase.getInstance().getReference();
        obtenerUsario(uid);


        edt_ControlNum = view.findViewById(R.id.edtVw_ControlNumber);
        edt_Name       = view.findViewById(R.id.edtVw_NameHome);
        edt_hbd        = view.findViewById(R.id.edtVw_HBD);
        edt_age        = view.findViewById(R.id.edtVw_age);
        edt_phoneNum   = view.findViewById(R.id.edtVw_phoneNumber);
        edt_colony     = view.findViewById(R.id.edtVw_Colony);
        edt_street     = view.findViewById(R.id.edtVw_street);
        edt_experience = view.findViewById(R.id.edtVw_Experience);
        edt_skills     = view.findViewById(R.id.edtVw_skills);
        edt_horaDisp   = view.findViewById(R.id.edtVw_scheduleAvailable);
        sp_Carrera     = view.findViewById(R.id.spVw_Carrera);
        sp_Semestre    = view.findViewById(R.id.spVw_Semestre);
        edt_nss        = view.findViewById(R.id.edtVw_NSS);

        //creamos un array para llenar el spinner
        String carreras[] = {"ISC","IGEM","ITICS","GASTRONOMIA","ARQUITECTURA","TURISMO","ELECTRO"};
        //nos vamos a layouts a crear nuestro propio spinner
        ArrayAdapter<String> adapter_carreras = new ArrayAdapter<String>(getContext(),R.layout.spinner_item,carreras);
        sp_Carrera.setEnabled(false);
        sp_Carrera.setClickable(false);
        this.sp_Carrera.setAdapter(adapter_carreras);

        String semestre[] = {"1° Semestre","2° Semestre","3° Semestre","4° Semestre","5° Semestre","6° Semestre","7° Semestre","8° Semestre","9° Semestre"};
        ArrayAdapter<String> adapter_semestre = new ArrayAdapter<String>(getContext(),R.layout.spinner_item,semestre);
        sp_Semestre.setEnabled(false);
        sp_Semestre.setClickable(false);
        this.sp_Semestre.setAdapter(adapter_semestre);


        rb_Matutino   = view.findViewById(R.id.rbVw_Matutino);
        rb_Vespertino = view.findViewById(R.id.rbVw_Vespertino);
        rb_Mixto      = view.findViewById(R.id.rbVw_Mixto);

        rb_Si         = view.findViewById(R.id.rbVw_Si);
        rb_No         = view.findViewById(R.id.rbVw_No);

        edt_ControlNum.setEnabled(false);
        edt_Name.setEnabled(false);
        edt_hbd.setEnabled(false);
        edt_age.setEnabled(false);
        edt_phoneNum.setEnabled(false);
        edt_colony.setEnabled(false);
        edt_street.setEnabled(false);
        edt_experience.setEnabled(false);
        edt_skills.setEnabled(false);
        edt_horaDisp.setEnabled(false);
        edt_nss.setEnabled(false);

        rb_Matutino.setEnabled(false);
        rb_Vespertino.setEnabled(false);
        rb_Mixto.setEnabled(false);

        rb_Si.setEnabled(false);
        rb_No.setEnabled(false);


        btn_Modify    = view.findViewById(R.id.btnVw_Modificar_Perfil);
        btn_Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.content_menu,new EditarFragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }


    //Metodo para obtener el usuario de firebase
    public void obtenerUsario(String uid)
    {
        myRef.child("DB_Alumnos").child(uid).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //verificamos si el dato al que queremos accesar existe
                if(dataSnapshot.exists())
                {
                    //el dataSnapshot ahora es el objeto uid del alumno, contiene todos los campos
                    String noControl    = dataSnapshot.child("noControl").getValue().toString();
                    String nombre       = dataSnapshot.child("nombre").getValue().toString();
                    String fecha        = dataSnapshot.child("fecha").getValue().toString();
                    String numTel       = dataSnapshot.child("numTel").getValue().toString();
                    String colonia      = dataSnapshot.child("colonia").getValue().toString();
                    String calle        = dataSnapshot.child("calle").getValue().toString();
                    String experiencia  = dataSnapshot.child("experiencia").getValue().toString();
                    String habilidades  = dataSnapshot.child("habilidades").getValue().toString();
                    String horariosDispo = dataSnapshot.child("horariosDispo").getValue().toString();
                    String carrera      = dataSnapshot.child("carrera").getValue().toString();
                    String semestre     = dataSnapshot.child("semestre").getValue().toString();
                    String turno        = dataSnapshot.child("turno").getValue().toString();
                    String statusTrabajo= dataSnapshot.child("statusTrabajo").getValue().toString();
                    String nss          = dataSnapshot.child("nss").getValue().toString();


                    edt_ControlNum.setText(noControl);
                    edt_Name.setText(nombre);
                    sp_Carrera.setSelection(obtenerCarreraId(carrera));
                    sp_Semestre.setSelection(obtenerSemestreId(semestre));

                    switch (turno) {
                        case "Matutino":
                            rb_Matutino.toggle();
                            break;
                        case "Vespertino":
                            rb_Vespertino.toggle();
                            break;
                        default:
                            rb_Mixto.toggle();
                            break;
                    }

                    edt_hbd.setText(fecha);
                   // edt_age.setText();
                    edt_nss.setText(nss);
                    edt_phoneNum.setText(numTel);
                    edt_colony.setText(colonia);
                    edt_street.setText(calle);

                    switch (statusTrabajo) {
                        case "Si":
                            rb_Si.toggle();
                            break;
                        case "No":
                            rb_No.toggle();
                            break;
                        default:

                            break;
                    }

                    edt_experience.setText(experiencia);
                    edt_skills.setText(habilidades);
                    edt_horaDisp.setText(horariosDispo);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }//fin metodo obtener usuario

    //Metodo para saber que carrera selecciono
    private int obtenerCarreraId(String carrera)
    {
        switch (carrera) {
            case "ISC":
                return 0;
            case "CIVIL":
                return 1;
            case "ITICS":
                return 2;
            case "IGEM":
                return 3;
            case "GASTRONOMIA":
                return 4;
            case "ARQUITECTURA":
                return 5;
            case "TURISMO":
                return 6;

            case "ELECTRO":
                return 7;
            default:
                return 0;
        }
    }//fin metodo Obtener Carrera



    //Metodo para saber que semestre va
    private int obtenerSemestreId(String semestre){
        switch (semestre) {

            case "1° Semestre":
                return 0;
            case "2° Semestre":
                return 1;
            case "3° Semestre":
                return 2;
            case "4° Semestre":
                return 3;
            case "5° Semestre":
                return 4;
            case "6° Semestre":
                return 5;
            case "7° Semestre":
                return 6;
            case "8° Semestre":
                return 7;
            case "9° Semestre":
                return 8;
            default:
                return 0;
        }
    }//fin del metodoobtenerSemestreId


}

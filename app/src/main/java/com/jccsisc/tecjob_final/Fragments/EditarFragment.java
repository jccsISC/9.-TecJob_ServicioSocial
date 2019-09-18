package com.jccsisc.tecjob_final.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jccsisc.tecjob_final.Activities.MenuPrincipalActivity;
import com.jccsisc.tecjob_final.R;
import com.jccsisc.tecjob_final.ValidacionUsuario;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditarFragment extends Fragment {

    private EditText edt_ControlNum,edt_Name,edt_hbd
            ,edt_age,edt_phoneNum,edt_colony,edt_street
            ,edt_experience,edt_skills,edt_horaDisp,edt_nss;
    private Spinner sp_Carrera, sp_Semestre;
    private RadioButton rb_Matutino, rb_Vespertino, rb_Mixto, rb_Si, rb_No;
    private Button btn_save;
    private ImageView btn_Imagen;

    private static final int GALERY_INTENT=1;

    //creamos la referencia
    private DatabaseReference myRef;
    private StorageReference myStorage;
    FirebaseAuth mAuth;


    public EditarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar, container, false);

        //con esto detecto al usuario actual
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getUid();

        //obtenemos la db de firebase
        myRef = FirebaseDatabase.getInstance().getReference();
        obtenerUsario(uid);
        myStorage = FirebaseStorage.getInstance().getReference();

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
        btn_Imagen     = view.findViewById(R.id.imgPerfil_editar);

        //creamos un array para llenar el spinner
        String carreras[] = {"ISC","CIVIL","IGEM","ITICS","GASTRONOMIA","ARQUITECTURA"};
        //nos vamos a layouts a crear nuestro propio spinner
        ArrayAdapter<String> adapter_carreras = new ArrayAdapter<String>(getContext(),R.layout.spinner_item,carreras);
        this.sp_Carrera.setAdapter(adapter_carreras);

        String semestre[] = {"1° Semestre","2° Semestre","3° Semestre","4° Semestre","5° Semestre","6° Semestre","7° Semestre","8° Semestre","9° Semestre"};
        ArrayAdapter<String> adapter_semestre = new ArrayAdapter<String>(getContext(),R.layout.spinner_item,semestre);
        this.sp_Semestre.setAdapter(adapter_semestre);


        rb_Matutino   = view.findViewById(R.id.rbVw_Matutino);
        rb_Vespertino = view.findViewById(R.id.rbVw_Vespertino);
        rb_Mixto      = view.findViewById(R.id.rbVw_Mixto);

        rb_Si         = view.findViewById(R.id.rbVw_Si);
        rb_No         = view.findViewById(R.id.rbVw_No);

        edt_age.setEnabled(false);
        sp_Carrera.setEnabled(false);
        sp_Semestre.setEnabled(false);

        btn_Imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //para abrir la galeria del dispositivo
                Intent intent =  new Intent(Intent.ACTION_PICK);
                //para este caso abarca todas las extensiones de imagenes
                intent.setType("image/*");
                startActivityForResult(intent, GALERY_INTENT);
            }
        });

        btn_save    = view.findViewById(R.id.btnVw_Save_Perfil);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatos();
                Intent intent = new Intent(getContext(), MenuPrincipalActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }//onCreate



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALERY_INTENT)
        {
            final Uri uri = data.getData();
            StorageReference filePath = myStorage.child("foto_perfil").child(uri.getLastPathSegment());

            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    taskSnapshot.getStorage().getDownloadUrl();

                    all("Se subió exitosamente la tu foto");

                }
            });
        }

    }


    //Metodo para obtener el usuario de firebase
    public void obtenerUsario(String uid)
    {
//        myRef.child("DB_Alumnos").child(uid).addValueEventListener(new ValueEventListener()
        myRef.child("DB_Alumnos").child(uid).addListenerForSingleValueEvent(new ValueEventListener()
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
                    String horariosDispo= dataSnapshot.child("horariosDispo").getValue().toString();
                    String carrera      = dataSnapshot.child("carrera").getValue().toString();
                    String semestre     = dataSnapshot.child("semestre").getValue().toString();
                    String turno        = dataSnapshot.child("turno").getValue().toString();
                    String statusTrabajo= dataSnapshot.child("statusTrabajo").getValue().toString();
                    String nss= dataSnapshot.child("nss").getValue().toString();
                    String foto = dataSnapshot.child("foto").getValue().toString();

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
                    edt_nss.setText(nss);
                    Picasso.get().load(foto)
                            .into(btn_Imagen);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }//fin metodo obtener usuario


    //Metodo para guardar los datos
    public void actualizarDatos()
    {
        ValidacionUsuario v = new ValidacionUsuario();
        //actualizar los datos
        String noControl     = edt_ControlNum.getText().toString();
        String nombre        = edt_Name.getText().toString();
        String fecha         = edt_hbd.getText().toString();
        String edad          = edt_age.getText().toString();
        String numTel        = edt_phoneNum.getText().toString();
        String colonia       = edt_colony.getText().toString();
        String calle         = edt_street.getText().toString();
        String experiencia   = edt_experience.getText().toString();
        String habilidades   = edt_skills.getText().toString();
        String horariosDispo  = edt_horaDisp.getText().toString();
        String carrera       = sp_Carrera.getSelectedItem().toString();
        String semestre      = sp_Semestre.getSelectedItem().toString();
        String nss           = edt_nss.getText().toString();
        Boolean rb_statusM   = rb_Matutino.isChecked();
        Boolean rb_statusV   = rb_Vespertino.isChecked();
        Boolean rb_statusMix = rb_Mixto.isChecked();
        Boolean rb_statusS   = rb_Si.isChecked();
        Boolean rb_statusN   = rb_No.isChecked();
        String turno         = " ";
        String statusTrabajo= " ";

        if(!TextUtils.isEmpty(noControl)){all("Ingrese su No Control"); return;}
        if(TextUtils.isEmpty(nombre)){all("Ingrese su nombre completo"); return;}
        if(v.validaionNombre(nombre)){all("Ingrese su nombre completo"); return;}



        if(rb_statusM.equals(true)){turno = "Matutino";}
        if(rb_statusV.equals(true)){turno = "Vespertino";}
        if(rb_statusMix.equals(true)){turno = "Mixto";}
        if(rb_statusS.equals(true)){statusTrabajo = "Si";}
        if(rb_statusN.equals(true)){statusTrabajo = "No";}

         //String id = myRef.push().getKey(); con esto creamos un nuevo nodo
            String id = mAuth.getUid();//con este le decimos a donde guarde

            Map<String, Object> alumnoMap = new HashMap<>();
            alumnoMap.put("calle",calle);
            alumnoMap.put("carrera",carrera);
            alumnoMap.put("colonia",colonia);
            alumnoMap.put("experiencia",experiencia);
            alumnoMap.put("fecha",fecha);
            alumnoMap.put("habilidades",habilidades);
            alumnoMap.put("horariosDispo",horariosDispo);
            alumnoMap.put("noControl",noControl);
            alumnoMap.put("nombre",nombre);
            alumnoMap.put("nss",nss);
            alumnoMap.put("numTel",numTel);
            alumnoMap.put("semestre",semestre);
            alumnoMap.put("statusTrabajo",statusTrabajo);
            alumnoMap.put("turno",turno);

            myRef.child("DB_Alumnos").child(id).updateChildren(alumnoMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getContext(),"Los datos se han actualizado correctamente",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(),"Ha ocurrido un error no se actualizaron los datos",Toast.LENGTH_SHORT).show();
                }
            });


    }//fin guardarDatos


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

    //metodo Toast
    public void all(String msj)
    {
        Toast toast = Toast.makeText(getContext(), msj, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        toast.show();
    }


}

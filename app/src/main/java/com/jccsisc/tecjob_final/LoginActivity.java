package com.jccsisc.tecjob_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.jccsisc.tecjob_final.Activities.MenuPrincipalActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_Login;
    private TextInputLayout tilCorreo;
    private TextInputLayout tilContrasena;
    private EditText edt_Correo;
    private EditText edt_Contrasena;
    private TextView tv_Recupera;
    private ImageView imageView;
    AlertDialog.Builder mBuilder2;
    ProgressBar progressBar;
    AlertDialog dialog2;
    ValidacionUsuario v = new ValidacionUsuario();
    private FirebaseAuth mAuth;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRef = FirebaseDatabase.getInstance().getReference("DB_Alumnos");
        mAuth = FirebaseAuth.getInstance();

        btn_Login         = findViewById(R.id.btnVw_login);
        edt_Correo        = findViewById(R.id.editText_Correo);
        edt_Contrasena    = findViewById(R.id.editText_Password);
        tv_Recupera       = findViewById(R.id.textView_RecuperaPssw);
        imageView         = findViewById(R.id.LogoConfirmar);
        tilCorreo = findViewById(R.id.tilCorreo);
        tilContrasena=findViewById(R.id.tilContrasena);
        progressBar       = findViewById(R.id.spin_kit);
        tv_Recupera.setOnClickListener(this);
        btn_Login.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnVw_login:
                login_User();
                break;

            case R.id.textView_RecuperaPssw:
                 mBuilder2 = new AlertDialog.Builder(LoginActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.cuadro_dialogo_resetpsswd, null);

                final EditText email = mView.findViewById(R.id.editText_Correo_Reset);
                Button btn_reset = mView.findViewById(R.id.btnVw_reset);
                btn_reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String correo = email.getText().toString();

                        if (TextUtils.isEmpty(correo)) {
                            msj("Ingrese su Correo!!");
                            return;
                        }
                        if (!v.validaCorreo(correo).find()) {
                            msj("Ingrese un correo Válido!!");
                            return;
                        }

                        resetPassword(correo);
                        email.setText("");
                    }
                });

                mBuilder2.setView(mView);
                dialog2 = mBuilder2.create();
                dialog2.show();
                break;
        }
    }


    private void resetPassword(String email)
    {
        //seleccionamos el idioma en que llegue el correo
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //validamos si el correo se envió correctamente
                if(task.isSuccessful())
                {
                    msj("Se envió un correo para restablecer tu contraseña");
                    dialog2.cancel();
                }else
                {
                    msj("No se pudo enviar el correo de recuperación de contraseña");
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        mostrarDialogoSalirApp();
    }

    private void mostrarDialogoSalirApp()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        builder.setMessage("Desea salir de la aplicación?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        //le asignamos un titulo a nuestra alerta
        AlertDialog titulo = builder.create();
        titulo.setTitle("Cerrar Aplicación");
        titulo.show();

    }

    //metodo onStart
    protected void onStart()
    {
        super.onStart();

        //para saber si el usuario ya inicio sesion
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }//fin metodo


    //Detecta si ya se inicio sesion
    private void updateUI(FirebaseUser currentUser) {

        if(currentUser!=null)
        {
            progressBar.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            edt_Correo.setVisibility(View.INVISIBLE);
            edt_Contrasena.setVisibility(View.INVISIBLE);
            tv_Recupera.setVisibility(View.INVISIBLE);
            btn_Login.setVisibility(View.INVISIBLE);
            tilContrasena.setVisibility(View.INVISIBLE);
            tilCorreo.setVisibility(View.INVISIBLE);

            mRef.getDatabase().getReference("DB_Alumnos").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("tokenAlumno").setValue(FirebaseInstanceId.getInstance().getToken()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent intent = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }
        else{
            //mostrar campos y ocultar spin kit

        }
    }

    //logear usuario
    public void login_User()
    {
        String email, password;
        email    = edt_Correo.getText().toString();
        password = edt_Contrasena.getText().toString();

        if(TextUtils.isEmpty(email)){
            msj("¡Ingrese su correo!."); return;}
        if(TextUtils.isEmpty(password)){
            msj("¡Ingrese su contraseña!."); return;}
        if(!v.validaCorreo(email).find()){
            msj("¡Ingrese un correo válido!."); return;}
        if(password.length()<=7){
            msj("Error al ingresar su contraseña (contraseña debe de tener entre 8 a 16)"); return;}

        //obtenemos lo escrito para ver si existen en firebase
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            msj("Bienvenido");
                            //si es correcto entra
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {

                            msj("Error de Internet ó usuario no existente");
                            updateUI(null);
                        }
                    }
                });
    }


    //metodo Toast
    public void msj(String msj)
    {
        Toast toast = Toast.makeText(this, msj, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        toast.show();
    }




}

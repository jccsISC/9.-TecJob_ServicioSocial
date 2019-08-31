package com.jccsisc.tecjob_final.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jccsisc.tecjob_final.Fragments.ContactanosFragment;
import com.jccsisc.tecjob_final.Fragments.FavoritosFragment;
import com.jccsisc.tecjob_final.Fragments.HomeFragment;
import com.jccsisc.tecjob_final.Fragments.PerfilFragment;
import com.jccsisc.tecjob_final.Fragments.ProcesosFragment;
import com.jccsisc.tecjob_final.LoginActivity;
import com.jccsisc.tecjob_final.Objetos_Firebase.Alumno;
import com.jccsisc.tecjob_final.Objetos_Firebase.ModeloAlumno;
import com.jccsisc.tecjob_final.R;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuPrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txt_NoControl, txt_Nombre, txt_Carrera;
    ImageView imagenInfoPersonal;
    FragmentTransaction fragmentTransaction;
    private DatabaseReference myRef;
    FirebaseAuth mAuth;

    boolean click =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        //con esto detecto al usuario actual
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getUid();

        //obtenemos la db de firebase
        myRef = FirebaseDatabase.getInstance().getReference();
        obtenerUsuario(uid);
//        getUserData();

     //   msj(FirebaseAuth.getInstance().getUid());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                click = !click;
                //validación para que gire 45° el icono y abra vista Mi orden
                if (click==true)
                {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                                android.R.interpolator.fast_out_slow_in);

//                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                        view.animate()
                                .rotation(click ? 180f : 0)
                                .setInterpolator(interpolador)
                                .start();

                        FragmentManager fm = getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.content_menu, new ProcesosFragment()).commit();
                    }
                }
                //validación para que gire 45° el icono y abra vista Home
                if (click==false)
                {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                                android.R.interpolator.fast_out_slow_in);

                        view.animate()
                                .rotation(click ? 180f : 0)
                                .setInterpolator(interpolador)
                                .start();

                        FragmentManager fm = getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.content_menu, new HomeFragment()).commit();
                    }
                }

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_home, R.string.navigation_perfil);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_menu,new PerfilFragment());
        fragmentTransaction.commit();



        FragmentManager fragment = getSupportFragmentManager();
        fragment.beginTransaction().replace(R.id.content_menu,new HomeFragment()).commit();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_menu,new HomeFragment());
        fragmentTransaction.commit();


        View headView = navigationView.getHeaderView( 0);
        txt_Nombre   = headView.findViewById(R.id.txtVw_nombre);
        txt_NoControl= headView.findViewById(R.id.txtVw_noControl);
        txt_Carrera = headView.findViewById(R.id.txtVw_carrera);
        imagenInfoPersonal = headView.findViewById(R.id.imagenInfoPersonal2);


    }//fin OnCreate

//    public void getUserData (){
//        myRef.getDatabase()
//                .getReference("DB_Alumnos")
//                .orderByChild("uid")
//                .equalTo(FirebaseAuth.getInstance().getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange( DataSnapshot dataSnapshot) {
//
//                        showData(dataSnapshot);
//
//                    }
//
//                    @Override
//                    public void onCancelled( DatabaseError databaseError) {
//
//                    }
//                });
//
//    }
//
//    public void showData(DataSnapshot snapshot){
//        for(DataSnapshot ds: snapshot.getChildren()){
//            ModeloAlumno uinfo = new ModeloAlumno();
//
//            uinfo.setNombre(ds.getValue(ModeloAlumno.class).getNombre());
//            uinfo.setFoto(ds.getValue(ModeloAlumno.class).getFoto());
//            uinfo.setNoControl(ds.getValue(ModeloAlumno.class).getNoControl());
//            uinfo.setCarrera(ds.getValue(ModeloAlumno.class).getCarrera());
//
//            Picasso.get().load(uinfo.getFoto()).into(imagenInfoPersonal);
//
//        }
//    }

    public void msj(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            mostrarDialogoSalirApp();
            //super.onBackPressed();
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fm = getSupportFragmentManager();

        if (id == R.id.nav_home) {
            fm.beginTransaction().replace(R.id.content_menu, new HomeFragment()).commit();
        } else if (id == R.id.nav_perfil) {
            fm.beginTransaction().replace(R.id.content_menu, new PerfilFragment()).commit();
        } else if (id == R.id.nav_favoritos) {
            fm.beginTransaction().replace(R.id.content_menu,new FavoritosFragment()).commit();
        } else if (id == R.id.nav_procesos) {
            fm.beginTransaction().replace(R.id.content_menu, new ProcesosFragment()).commit();
        } else if (id == R.id.nav_contactanos) {
            fm.beginTransaction().replace(R.id.content_menu, new ContactanosFragment()).commit();
        } else if (id == R.id.nav_cerrarsesion) {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MenuPrincipalActivity.this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void obtenerUsuario(String uid)
    {
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
                    String carrera      = dataSnapshot.child("carrera").getValue().toString();
                    String foto       = dataSnapshot.child("foto").getValue().toString();


                    txt_NoControl.setText(noControl);
                    txt_Nombre.setText(nombre);
                    txt_Carrera.setText(carrera);
                    //msj(foto);
                    Picasso.get().load(foto)
                        .into(imagenInfoPersonal);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }//fin metodo obtener usuario


    private void mostrarDialogoSalirApp()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipalActivity.this);

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



}

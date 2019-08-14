package com.jccsisc.tecjob_final.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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
import com.jccsisc.tecjob_final.Fragments.FavoritosFragment;
import com.jccsisc.tecjob_final.Modelos.Empresa_Modelo;
import com.jccsisc.tecjob_final.Objetos_Firebase.Favoritos;
import com.jccsisc.tecjob_final.Objetos_Firebase.OfertasEmpresa;
import com.jccsisc.tecjob_final.R;

import java.util.List;

public class Favorito_Adapter extends RecyclerView.Adapter<Favorito_Adapter.FavoritosViewHolder>
{


    //creamos una lista de empresas
    List<Favoritos> favoritos_modelo;
    private int resource;
    private Activity activity;

    //constructor
    public Favorito_Adapter(List<Favoritos> favoritos_modelos, Activity activity, int resource)
    {
        this.favoritos_modelo = favoritos_modelos;
        this.activity = activity;
        this.resource = resource;
    }

    //creamos la vista para el recycler
    @NonNull
    @Override
    public FavoritosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_empresas,parent,false);

        FavoritosViewHolder holder = new FavoritosViewHolder(v);



        return holder;
    }


    //aqui asignamos los valores a las vistas
    @Override
    public void onBindViewHolder(@NonNull FavoritosViewHolder favoritosViewHolder, int position) {

        //optenemos la posicion de la lista
        final Favoritos favorito_modelos = favoritos_modelo.get(position);

    }

    //cremos el tamaño del recycler
    @Override
    public int getItemCount() {

        if(favoritos_modelo.size()>0)
        {
            return favoritos_modelo.size();
        }

        return 0;
    }

    //Primer clase que tenemos q creamos nuestra innerclas
    public static class FavoritosViewHolder extends RecyclerView.ViewHolder
    {


        public FavoritosViewHolder(View itemView) {
            super(itemView);

        }

    }

}//FIN DE LA CLASE
package com.jccsisc.tecjob_final.Objetos_Firebase;

public class Favoritos
{
    String   empresa, fecha_publicada, foto, nombre_puesto, uid_empresa, turno;

    public Favoritos(){}

    public Favoritos( String foto, String fecha_publicada, String nombre_puesto, String turno, String empresa) {
        this.foto = foto;
        this.fecha_publicada = fecha_publicada;
        this.nombre_puesto = nombre_puesto;
        this.turno = turno;
        this.empresa = empresa;
    }



    public String getUid_empresa() {
        return uid_empresa;
    }

    public void setUid_empresa(String uid_empresa) {
        this.uid_empresa = uid_empresa;
    }

    public String getFecha_publicada() {
        return fecha_publicada;
    }

    public void setFecha_publicada(String fecha_publicada) {
        this.fecha_publicada = fecha_publicada;
    }

    public String getNombre_puesto() {
        return nombre_puesto;
    }

    public void setNombre_puesto(String nombre_puesto) {
        this.nombre_puesto = nombre_puesto;
    }


    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEmpresa() {
        return empresa;
    }




    @Override
    public String toString() {
        return "Favoritos{" +
                "foto='" + foto + '\'' +
                ", nombre_puesto='" + nombre_puesto + '\'' +
                ", empresa='" + empresa + '\'' +
                ", fecha_publicada= '" + fecha_publicada + '\'' +
                ",turno" + turno+
                '}';
    }

}

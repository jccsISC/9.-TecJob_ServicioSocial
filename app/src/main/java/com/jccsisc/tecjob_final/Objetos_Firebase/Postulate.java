package com.jccsisc.tecjob_final.Objetos_Firebase;

public class Postulate
{
    String uid_empresa, estatus, foto, nombre_empresa, puesto;


    public Postulate(){}

    public Postulate(String estatus, String foto, String nombre_empresa, String puesto) {
        this.estatus = estatus;
        this.foto = foto;
        this.nombre_empresa = nombre_empresa;
        this.puesto = puesto;
    }

    public String getUid_empresa() {
        return uid_empresa;
    }

    public void setUid_empresa(String uid_empresa) {
        this.uid_empresa = uid_empresa;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}

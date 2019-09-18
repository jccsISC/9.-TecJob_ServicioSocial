package com.jccsisc.tecjob_final.Objetos_Firebase;

public class Proceso_Modelo
{
    String foto;

    String empresa;

    String nombre_puesto;

    String status;

    String uid_oferta;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre_empresa() {
        return empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.empresa = nombre_empresa;
    }

    public String getNombre_puesto() {
        return nombre_puesto;
    }

    public void setNombre_puesto(String nombre_puesto) {
        this.nombre_puesto = nombre_puesto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid_oferta() {
        return uid_oferta;
    }

    public void setUid_oferta(String uid_oferta) {
        this.uid_oferta = uid_oferta;
    }

    public void postularme(String foto,String nombre_puesto, String status, String uid_oferta, String empresa){
        this.foto = foto;
        this.nombre_puesto = nombre_puesto;
        this.status = status;
        this.uid_oferta = uid_oferta;
        this.empresa = empresa;

    }
}

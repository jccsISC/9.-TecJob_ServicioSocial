package com.jccsisc.tecjob_final.Objetos_Firebase;

public class Favoritos
{
    String Horario, carrera, empresa, fecha_publicada, foto, puesto, uid_empresa;

    public Favoritos(){}

    public Favoritos(String horario, String carrera, String empresa, String fecha_publicada, String foto, String puesto) {
        Horario = horario;
        this.carrera = carrera;
        this.empresa = empresa;
        this.fecha_publicada = fecha_publicada;
        this.foto = foto;
        this.puesto = puesto;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFecha_publicada() {
        return fecha_publicada;
    }

    public void setFecha_publicada(String fecha_publicada) {
        this.fecha_publicada = fecha_publicada;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getUid_empresa() {
        return uid_empresa;
    }

    public void setUid_empresa(String uid_empresa) {
        this.uid_empresa = uid_empresa;
    }
}

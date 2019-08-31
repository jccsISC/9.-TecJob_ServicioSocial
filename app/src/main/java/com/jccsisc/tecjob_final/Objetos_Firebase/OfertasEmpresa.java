package com.jccsisc.tecjob_final.Objetos_Firebase;

public class OfertasEmpresa
{
    String fecha_publicada,nombre_puesto,turno, empresa, uid_empresa, foto, status, uid_oferta;

    public  OfertasEmpresa(){}



    public OfertasEmpresa(String foto, String fecha_publicada, String nombre_puesto, String turno, String empresa) {
        this.foto = foto;
        this.fecha_publicada = fecha_publicada;
        this.nombre_puesto = nombre_puesto;
        this.turno = turno;
        this.empresa = empresa;
    }

    public String getUid_oferta() {
        return uid_oferta;
    }

    public void setUid_oferta(String uid_oferta) {
        this.uid_oferta = uid_oferta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "OfertasEmpresa{" +
                "foto='" + foto + '\'' +
                ", nombre_puesto='" + nombre_puesto + '\'' +
                ", empresa='" + empresa + '\'' +
                ", fecha_publicada= '" + fecha_publicada + '\'' +
                ",turno" + turno+
                '}';
    }
}

package com.jccsisc.tecjob_final.Objetos_Firebase;

public class OfertasEmpresa
{
    String fecha_publicada,nombre_puesto,turno, empresa, uid_empresa, foto, status, uid_oferta, razon_social, habilidades, requisitos, domicilio, salario_mensual, contacto, desc_puesto;
    boolean favorito;

    public  OfertasEmpresa(){}



    public OfertasEmpresa(String foto, String fecha_publicada, String nombre_puesto, String turno, String empresa) {
        this.foto = foto;
        this.fecha_publicada = fecha_publicada;
        this.nombre_puesto = nombre_puesto;
        this.turno = turno;
        this.empresa = empresa;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getSalario_mensual() {
        return salario_mensual;
    }

    public void setSalario_mensual(String salario_mensual) {
        this.salario_mensual = salario_mensual;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDesc_puesto() {
        return desc_puesto;
    }

    public void setDesc_puesto(String desc_puesto) {
        this.desc_puesto = desc_puesto;
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

    public boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
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

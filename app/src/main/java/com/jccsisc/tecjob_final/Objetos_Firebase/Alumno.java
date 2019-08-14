package com.jccsisc.tecjob_final.Objetos_Firebase;

public class Alumno
{
    String calle, carrera, colonia,
           experiencia, fecha, habilidades,
           horariosDispo, noControl, nombre,
           nss, numTel, semestre, statusTrabajo,turno;

    public Alumno(String calle, String carrera, String colonia, String experiencia, String fecha, String habilidades, String horariosDispo, String noControl, String nombre, String nss, String numTel, String semestre, String statusTrabajo, String turno) {
        this.calle = calle;
        this.carrera = carrera;
        this.colonia = colonia;
        this.experiencia = experiencia;
        this.fecha = fecha;
        this.habilidades = habilidades;
        this.horariosDispo = horariosDispo;
        this.noControl = noControl;
        this.nombre = nombre;
        this.nss = nss;
        this.numTel = numTel;
        this.semestre = semestre;
        this.statusTrabajo = statusTrabajo;
        this.turno = turno;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getHorariosDispo() {
        return horariosDispo;
    }

    public void setHorariosDispo(String horariosDispo) {
        this.horariosDispo = horariosDispo;
    }

    public String getNoControl() {
        return noControl;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getStatusTrabajo() {
        return statusTrabajo;
    }

    public void setStatusTrabajo(String statusTrabajo) {
        this.statusTrabajo = statusTrabajo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}

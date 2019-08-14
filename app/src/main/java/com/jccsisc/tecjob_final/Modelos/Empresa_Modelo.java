package com.jccsisc.tecjob_final.Modelos;

public class Empresa_Modelo
{
    String nombre, vacante,horapublicado;

    public Empresa_Modelo(String nombre, String vacante, String horapublicado) {
        this.nombre = nombre;
        this.vacante = vacante;
        this.horapublicado = horapublicado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVacante() {
        return vacante;
    }

    public void setVacante(String vacante) {
        this.vacante = vacante;
    }

    public String getHorapublicado() {
        return horapublicado;
    }

    public void setHorapublicado(String horapublicado) {
        this.horapublicado = horapublicado;
    }
}

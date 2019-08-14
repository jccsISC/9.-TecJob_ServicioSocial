package com.jccsisc.tecjob_final.Modelos;

public class Proceso_Modelo
{
    String nombre, vacante,salario;

    public Proceso_Modelo(String nombre, String vacante, String salario) {
        this.nombre = nombre;
        this.vacante = vacante;
        this.salario = salario;
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

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }
}

package com.cloudnets.cloudacademic.Models;

/**
 * Created by USER on 01/07/2015.
 */
public class Proceso {

    /*************Atributos del objeto resultado*************/
    boolean resultado = false;
    String titulo = "";
    String mensaje = "";

    /*********Getters y Setters del objeto resultado*********/
    public boolean isResultado() {
        return resultado;
    }
    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}


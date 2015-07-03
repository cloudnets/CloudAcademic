package com.cloudnets.cloudacademic.Models;

/**
 * Created by Deimer on 01/07/2015.
 * ----------------------------------------------------
 * Clase modelo del objeto Institucion.
 * Esta clase es la encargada de abarcar el modelo para
 * agregar datos dinamicos y trabajar con ellos.
 */
public class Institucion {

    //Variables del modelo
    private String nombre = "";

    //Constructor del objeto para agregar recursos
    public Institucion(String nombre){
        this.nombre = nombre;
    }

    //Getters y Setters del objeto Institucion
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
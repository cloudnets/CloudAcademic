package com.cloudnets.cloudacademic.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Creado por Deimer Villa on 06/07/2015.
 * ----------------------------------------------------
 * Clase modelo del objeto grado:
 * Esta clase usa la libreria ORMLite para manejar la relacion
 * de la clase grado con la tabla grados en la base de datos.
 * Cualquier atributo o propiedad del objeto grado, debe ser agregado
 * aqui para que este se agregue en la base de datos.
 */
@DatabaseTable(tableName = "Grados")
public class Grado {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(index = true, canBeNull = false)
    private String nombre;
    @DatabaseField(canBeNull = false)
    private String orden;
    @DatabaseField(canBeNull = false)
    private String cod_nivelEdu;

    public void Grado(){}

    public Grado(String nombre, String orden, String cod_nivelEdu) {
        this.setNombre(nombre);
        this.setOrden(orden);
        this.setCod_nivelEdu(cod_nivelEdu);
    }

    //Getters del modelo grado
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getOrden() {
        return orden;
    }
    public String getCod_nivelEdu() {
        return cod_nivelEdu;
    }

    //Setters del modelo grado
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setOrden(String orden) {
        this.orden = orden;
    }
    public void setCod_nivelEdu(String cod_nivelEdu) {
        this.cod_nivelEdu = cod_nivelEdu;
    }
}

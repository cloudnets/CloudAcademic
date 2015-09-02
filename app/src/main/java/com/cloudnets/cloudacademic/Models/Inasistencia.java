package com.cloudnets.cloudacademic.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Creado por Deimer Villa on 31/08/2015.
 * ----------------------------------------------------
 * Clase modelo del objeto asignatura:
 * Esta clase usa la libreria ORMLite para manejar la relacion
 * de la clase inasistencia con la tabla asignaturas en la base de datos.
 * Cualquier atributo o propiedad del objeto inasistencia, debe ser agregado
 * aqui para que este se agregue en la base de datos.
 */
@DatabaseTable(tableName = "inasistencias")
public class Inasistencia {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(canBeNull = false)
    private String codgradoasig;
    @DatabaseField(canBeNull = false)
    private String codestumatricula;
    @DatabaseField(canBeNull = false)
    private String observaciones;

    public Inasistencia(){}

    public Inasistencia(String codgradoasig, String codestumatricula, String observaciones) {
        this.codgradoasig = codgradoasig;
        this.codestumatricula = codestumatricula;
        this.observaciones = observaciones;
    }

    //Getters del modelo inasistencia
    public int getId() {
        return id;
    }
    public String getCodgradoasig() {
        return codgradoasig;
    }
    public String getCodestumatricula() {
        return codestumatricula;
    }
    public String getObservaciones() {
        return observaciones;
    }

    //Setters del modelo inasistencia
    public void setCodgradoasig(String codgradoasig) {
        this.codgradoasig = codgradoasig;
    }
    public void setCodestumatricula(String codestumatricula) {
        this.codestumatricula = codestumatricula;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

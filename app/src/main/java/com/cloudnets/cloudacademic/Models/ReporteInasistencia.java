package com.cloudnets.cloudacademic.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Creado por Deimer Villa on 31/08/2015.
 */
@DatabaseTable(tableName = "reportes")
public class ReporteInasistencia {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(canBeNull = false)
    private String idInasistencia;
    @DatabaseField(canBeNull = false)
    private String nombreCompleto;
    @DatabaseField(canBeNull = false)
    private String identificacion;

    public ReporteInasistencia(){}

    public ReporteInasistencia(String idInasistencia, String nombreCompleto, String identificacion) {
        this.idInasistencia = idInasistencia;
        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
    }

    //Getters del reporte
    public int getId() {
        return id;
    }
    public String getIdInasistencia() {
        return idInasistencia;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public String getIdentificacion() {
        return identificacion;
    }

    //Setters del reporte
    public void setIdInasistencia(String idInasistencia) {
        this.idInasistencia = idInasistencia;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}

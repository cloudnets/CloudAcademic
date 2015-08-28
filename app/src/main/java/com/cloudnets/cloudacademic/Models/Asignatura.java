package com.cloudnets.cloudacademic.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Creado por Deimer Villa on 03/08/2015.
 * ----------------------------------------------------
 * Clase modelo del objeto asignatura:
 * Esta clase usa la libreria ORMLite para manejar la relacion
 * de la clase asignatura con la tabla asignaturas en la base de datos.
 * Cualquier atributo o propiedad del objeto asignatura, debe ser agregado
 * aqui para que este se agregue en la base de datos.
 */
@DatabaseTable(tableName = "asignaturas")
public class Asignatura {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(canBeNull = false)
    private String codasignatura;
    @DatabaseField(canBeNull = false)
    private String nombre;
    @DatabaseField(canBeNull = false)
    private String abreviatura;
    @DatabaseField(canBeNull = false)
    private String curso;
    @DatabaseField(canBeNull = false)
    private String intensidad_horaria;
    @DatabaseField(canBeNull = false)
    private String porcentaje;

    public Asignatura(){}

    public Asignatura(String codasignatura, String nombre, String abreviatura, String curso,
                      String intensidad_horaria, String porcentaje) {
        this.codasignatura = codasignatura;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.curso = curso;
        this.intensidad_horaria = intensidad_horaria;
        this.porcentaje = porcentaje;
    }

    public int getId() {
        return id;
    }
    public String getCodasignatura() {
        return codasignatura;
    }
    public String getNombre() {
        return nombre;
    }
    public String getAbreviatura() {
        return abreviatura;
    }
    public String getCurso() {
        return curso;
    }
    public String getIntensidad_horaria() {
        return intensidad_horaria;
    }
    public String getPorcentaje() {
        return porcentaje;
    }

    public void setCodasignatura(String codasignatura) {
        this.codasignatura = codasignatura;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public void setIntensidad_horaria(String intensidad_horaria) {
        this.intensidad_horaria = intensidad_horaria;
    }
    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
}

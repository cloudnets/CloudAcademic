package com.cloudnets.cloudacademic.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Creado por Deimer Villa on 29/08/2015.
 * ----------------------------------------------------
 * Clase modelo del objeto evento:
 * Esta clase usa la libreria ORMLite para manejar la relacion
 * de la clase evento con la tabla eventos en la base de datos.
 * Cualquier atributo o propiedad del objeto evento, debe ser agregado
 * aqui para que este se agregue en la base de datos.
 */
@DatabaseTable(tableName = "eventos")
public class Evento {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(canBeNull = false)
    public String codigo;
    @DatabaseField(canBeNull = false)
    public String nombre_actividad;
    @DatabaseField(canBeNull = false)
    public String descripcion;
    @DatabaseField(canBeNull = false)
    public String startday;
    @DatabaseField(canBeNull = false)
    public String endday;
    @DatabaseField(canBeNull = false)
    public String cod_docente;
    @DatabaseField(canBeNull = false)
    public String curso;
    @DatabaseField(canBeNull = false)
    public String asignatura;
    @DatabaseField(canBeNull = false)
    public String createdday;

    public Evento(){}

    public Evento(String codigo, String nombre_actividad, String descripcion, String startday,
                  String endday, String cod_docente, String curso, String asignatura, String createdday) {
        this.codigo = codigo;
        this.nombre_actividad = nombre_actividad;
        this.descripcion = descripcion;
        this.startday = startday;
        this.endday = endday;
        this.cod_docente = cod_docente;
        this.curso = curso;
        this.asignatura = asignatura;
        this.createdday = createdday;
    }

    //Getters del objeto evento
    public int getId() {
        return id;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNombre_actividad() {
        return nombre_actividad;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getStartday() {
        return startday;
    }
    public String getEndday() {
        return endday;
    }
    public String getCod_docente() {
        return cod_docente;
    }
    public String getCurso() {
        return curso;
    }
    public String getAsignatura() {
        return asignatura;
    }
    public String getCreatedday() {
        return createdday;
    }

    //Setters del objeto evento
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setStartday(String startday) {
        this.startday = startday;
    }
    public void setEndday(String endday) {
        this.endday = endday;
    }
    public void setCod_docente(String cod_docente) {
        this.cod_docente = cod_docente;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
    public void setCreatedday(String createdday) {
        this.createdday = createdday;
    }

    @Override
    public String toString() {
        return "Evento{" +
            "id=" + id +
            ", codigo='" + codigo + '\'' +
            ", nombre_actividad='" + nombre_actividad + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", startday='" + startday + '\'' +
            ", endday='" + endday + '\'' +
            ", cod_docente='" + cod_docente + '\'' +
            ", curso='" + curso + '\'' +
            ", asignatura='" + asignatura + '\'' +
            ", createdday='" + createdday + '\'' +
        '}';
    }
}

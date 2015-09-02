package com.cloudnets.cloudacademic.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Creado por Deimer Villa on 03/08/2015.
 * ----------------------------------------------------
 * Clase modelo del objeto curso:
 * Esta clase usa la libreria ORMLite para manejar la relacion
 * de la clase perfil con la tabla cursos en la base de datos.
 * Cualquier atributo o propiedad del objeto curso, debe ser agregado
 * aqui para que este se agregue en la base de datos.
 */
@DatabaseTable(tableName = "cursos")
public class Curso {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(index = true, canBeNull = false)
    private String codigo;
    @DatabaseField(index = true, canBeNull = false)
    private String descripcion;
    @DatabaseField(index = true, canBeNull = true)
    private String inthoraria;
    @DatabaseField(index = true, canBeNull = false)
    private String usucoordinador;

    public Curso(){}

    public Curso(String codigo, String descripcion, String inthoraria, String codcoordinador) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.inthoraria = inthoraria;
        this.usucoordinador = codcoordinador;
    }

    //Getters del objeto curso
    public int getId() {
        return id;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getInthoraria() {
        return inthoraria;
    }
    public String getUsucoordinador() {
        return usucoordinador;
    }

    //Setters del objeto curso
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setInthoraria(String inthoraria) {
        this.inthoraria = inthoraria;
    }
    public void setUsucoordinador(String usucoordinador) {
        this.usucoordinador = usucoordinador;
    }

    @Override
    public String toString() {
        return "Curso{" +
            "id=" + id +
            ", codigo='" + codigo + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", inthoraria='" + inthoraria + '\'' +
            ", usucoordinador='" + usucoordinador + '\'' +
        '}';
    }
}

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
@DatabaseTable(tableName = "grados")
public class Grado {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(index = true, canBeNull = false)
    private String nombre;
    @DatabaseField(canBeNull = false)
    private String codigo;
    @DatabaseField(index = true, canBeNull = false)
    private String cod_docente;

    public void Grado(){}

    public Grado(String nombre, String codigo, String cod_docente) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cod_docente = cod_docente;
    }

    //Getters para obtener los valores del modelo grado
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getCod_docente() {
        return cod_docente;
    }

    //Setters para agregar valores al modelo grado
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setCod_docente(String cod_docente) {
        this.cod_docente = cod_docente;
    }
}

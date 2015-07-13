package com.cloudnets.cloudacademic.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Creado por Deimer Villa on 06/07/2015.
 * ----------------------------------------------------
 * Clase modelo del objeto estudiante:
 * Esta clase usa la libreria ORMLite para manejar la relacion
 * de la clase estudiante con la tabla estudiantess en la base de datos.
 * Cualquier atributo o propiedad del objeto estudiante, debe ser agregado
 * aqui para que este se agregue en la base de datos.
 */
@DatabaseTable(tableName = "estudiantes")
public class Estudiante {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(index = true, canBeNull = false)
    private String identificacion;
    @DatabaseField(canBeNull = false)
    private String tipo_id;
    @DatabaseField(index = true, canBeNull = false)
    private String primer_nombre;
    @DatabaseField(canBeNull = false)
    private String segundo_nombre;
    @DatabaseField(index = true, canBeNull = false)
    private String primer_apellido;
    @DatabaseField(canBeNull = false)
    private String segundo_apellido;
    @DatabaseField(canBeNull = false)
    private Date fecha_nacimiento;
    @DatabaseField(canBeNull = false)
    private String genero;
    @DatabaseField(canBeNull = false)
    private String direccion;
    @DatabaseField(canBeNull = false)
    private String telefono;
    @DatabaseField(canBeNull = false)
    private String celular;
    @DatabaseField(canBeNull = false)
    private String estrato;
    @DatabaseField(canBeNull = false)
    private String eps;
    @DatabaseField(canBeNull = false)
    private String nom_grado;
    @DatabaseField(index = true, canBeNull = false)
    private String cod_grado;

    public void Estudiante(){}

    public Estudiante(String identificacion, String tipo_id, String primer_nombre, String segundo_nombre,
                      String primer_apellido, String segundo_apellido, Date fecha_nacimiento,
                      String genero, String direccion, String telefono, String celular, String estrato,
                      String eps, String nom_grado, String cod_grado) {
        this.identificacion = identificacion;
        this.tipo_id = tipo_id;
        this.primer_nombre = primer_nombre;
        this.segundo_nombre = segundo_nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.estrato = estrato;
        this.eps = eps;
        this.nom_grado = nom_grado;
        this.cod_grado = cod_grado;
    }

    //Getters del modelo estudiante
    public int getId() {
        return id;
    }
    public String getIdentificacion() {
        return identificacion;
    }
    public String getTipo_id() {
        return tipo_id;
    }
    public String getPrimer_nombre() {
        return primer_nombre;
    }
    public String getSegundo_nombre() {
        return segundo_nombre;
    }
    public String getPrimer_apellido() {
        return primer_apellido;
    }
    public String getSegundo_apellido() {
        return segundo_apellido;
    }
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public String getGenero() {
        return genero;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getCelular() {
        return celular;
    }
    public String getEstrato() {
        return estrato;
    }
    public String getEps() {
        return eps;
    }
    public String getNom_grado() {
        return nom_grado;
    }
    public String getCod_grado() {
        return cod_grado;
    }

    //Setters del modelo estudiante
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }
    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }
    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }
    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }
    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }
    public void setEps(String eps) {
        this.eps = eps;
    }
    public void setNom_grado(String nom_grado) {
        this.nom_grado = nom_grado;
    }
    public void setCod_grado(String cod_grado) {
        this.cod_grado = cod_grado;
    }

}

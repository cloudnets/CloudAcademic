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
@DatabaseTable(tableName = "Estudiantes")
public class Estudiante {

    /*identificacion, tipo_id, primerNombre, segundoNombre, primerApellido, fechaNacimiento,
      genero, direccion, telefono, celular, estrato, cod_eps, cod_padres*/

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
    private String cod_eps;
    @DatabaseField(canBeNull = false)
    private String cod_padre;

    public void Estudiante(){}

    public Estudiante(String identificacion, String tipo_id, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido, Date fecha_nacimiento, String genero, String direccion, String telefono, String celular, String estrato, String cod_eps, String cod_padre) {
        this.setIdentificacion(identificacion);
        this.setTipo_id(tipo_id);
        this.setPrimer_nombre(primer_nombre);
        this.setSegundo_nombre(segundo_nombre);
        this.setPrimer_apellido(primer_apellido);
        this.setSegundo_apellido(segundo_apellido);
        this.setFecha_nacimiento(fecha_nacimiento);
        this.setGenero(genero);
        this.setDireccion(direccion);
        this.setTelefono(telefono);
        this.setCelular(celular);
        this.setEstrato(estrato);
        this.setCod_eps(cod_eps);
        this.setCod_padre(cod_padre);
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
    public String getCod_eps() {
        return cod_eps;
    }
    public String getCod_padre() {
        return cod_padre;
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
    public void setCod_eps(String cod_eps) {
        this.cod_eps = cod_eps;
    }
    public void setCod_padre(String cod_padre) {
        this.cod_padre = cod_padre;
    }
}

package com.cloudnets.cloudacademic.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Creado por Deimer Villa on 03/08/2015.
 * ----------------------------------------------------
 * Clase modelo del objeto estudiante:
 * Esta clase usa la libreria ORMLite para manejar la relacion
 * de la clase estudiante con la tabla estudiantes en la base de datos.
 * Cualquier atributo o propiedad del objeto estudiante, debe ser agregado
 * aqui para que este se agregue en la base de datos.
 */
@DatabaseTable(tableName = "estudiantes")
public class Estudiante {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(canBeNull = false)
    private String identificacion;
    @DatabaseField(canBeNull = false)
    private String tipo_id;
    @DatabaseField(canBeNull = false)
    private String primernombre;
    @DatabaseField(canBeNull = true)
    private String segundonombre;
    @DatabaseField(canBeNull = false)
    private String primerapellido;
    @DatabaseField(canBeNull = true)
    private String segundoapellido;
    @DatabaseField(canBeNull = false)
    private String fechanacimiento;
    @DatabaseField(canBeNull = false)
    private String genero;
    @DatabaseField(canBeNull = true)
    private String direccion;
    @DatabaseField(canBeNull = true)
    private String telefono;
    @DatabaseField(canBeNull = true)
    private String celular;
    @DatabaseField(canBeNull = true)
    private String estrato;
    @DatabaseField(canBeNull = true)
    private String eps;
    @DatabaseField(canBeNull = false)
    private String nom_grado;
    @DatabaseField(canBeNull = false)
    private String cod_grado;

    //Constructor de la entidad estudiante
    public Estudiante(){}

    //Constructor del objeto para los datos de inicializacion
    public Estudiante(String identificacion, String tipo_id, String primer_nombre,
                      String segundo_nombre, String primer_apellido, String segundo_apellido,
                      String fecha_nacimiento, String genero, String direccion, String telefono,
                      String celular, String estrato, String eps, String nom_grado, String cod_grado) {
        this.identificacion = identificacion;
        this.tipo_id = tipo_id;
        this.primernombre = primer_nombre;
        this.segundonombre = segundo_nombre;
        this.primerapellido = primer_apellido;
        this.segundoapellido = segundo_apellido;
        this.fechanacimiento = fecha_nacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.estrato = estrato;
        this.eps = eps;
        this.nom_grado = nom_grado;
        this.cod_grado = cod_grado;
    }

    //Getters del objeto estudiante
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
        return primernombre;
    }
    public String getSegundo_nombre() {
        return segundonombre;
    }
    public String getPrimer_apellido() {
        return primerapellido;
    }
    public String getSegundo_apellido() {
        return segundoapellido;
    }
    public String getFecha_nacimiento() {
        return fechanacimiento;
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

    //Setters del objeto estudiante
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }
    public void setPrimer_nombre(String primer_nombre) {
        this.primernombre = primer_nombre;
    }
    public void setSegundo_nombre(String segundo_nombre) {
        this.segundonombre = segundo_nombre;
    }
    public void setPrimer_apellido(String primer_apellido) {
        this.primerapellido = primer_apellido;
    }
    public void setSegundo_apellido(String segundo_apellido) {
        this.segundoapellido = segundo_apellido;
    }
    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fechanacimiento = fecha_nacimiento;
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

    @Override
    public String toString() {
        return "Estudiante{" +
            "id=" + id +
            ", identificacion='" + identificacion + '\'' +
            ", tipo_id='" + tipo_id + '\'' +
            ", primer_nombre='" + primernombre + '\'' +
            ", segundo_nombre='" + segundonombre + '\'' +
            ", primer_apellido='" + primerapellido + '\'' +
            ", segundo_apellido='" + segundoapellido + '\'' +
            ", fecha_nacimiento=" + fechanacimiento +
            ", genero='" + genero + '\'' +
            ", direccion='" + direccion + '\'' +
            ", telefono='" + telefono + '\'' +
            ", celular='" + celular + '\'' +
            ", estrato='" + estrato + '\'' +
            ", eps='" + eps + '\'' +
            ", nom_grado='" + nom_grado + '\'' +
            ", cod_grado='" + cod_grado + '\'' +
        '}';
    }
}

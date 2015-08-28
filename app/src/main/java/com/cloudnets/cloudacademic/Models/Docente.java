package com.cloudnets.cloudacademic.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Creado por Deimer Villa on 05/08/2015.
 * ----------------------------------------------------
 * Clase modelo del objeto docente:
 * Esta clase usa la libreria ORMLite para manejar la relacion
 * de la clase docente con la tabla docentes en la base de datos.
 * Cualquier atributo o propiedad del objeto docente, debe ser agregado
 * aqui para que este se agregue en la base de datos.
 */
@DatabaseTable(tableName = "docentes")
public class Docente {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(canBeNull = false)
    private String identificacion;
    @DatabaseField(canBeNull = true)
    private String fullname;
    @DatabaseField(canBeNull = true)
    private String fechanacimiento;
    @DatabaseField(canBeNull = true)
    private String fechavinculacion;
    @DatabaseField(canBeNull = true)
    private String email;
    @DatabaseField(canBeNull = true)
    private String genero;
    @DatabaseField(canBeNull = true)
    private String telefono;
    @DatabaseField(canBeNull = true)
    private String celular;
    @DatabaseField(canBeNull = true)
    private String direccion;
    @DatabaseField(canBeNull = true)
    private String estado;

    public Docente(){}

    public Docente(String identificacion, String fullname, String fechanacimiento,
                   String fechavinculacion, String email, String genero, String telefono,
                   String celular, String direccion, String estado) {
        this.identificacion = identificacion;
        this.fullname = fullname;
        this.fechanacimiento = fechanacimiento;
        this.fechavinculacion = fechavinculacion;
        this.email = email;
        this.genero = genero;
        this.telefono = telefono;
        this.celular = celular;
        this.direccion = direccion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }
    public String getIdentificacion() {
        return identificacion;
    }
    public String getFullname() {
        return fullname;
    }
    public String getFechanacimiento() {
        return fechanacimiento;
    }
    public String getFechavinculacion() {
        return fechavinculacion;
    }
    public String getEmail() {
        return email;
    }
    public String getGenero() {
        return genero;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getCelular() {
        return celular;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getEstado() {
        return estado;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    public void setFechavinculacion(String fechavinculacion) {
        this.fechavinculacion = fechavinculacion;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}

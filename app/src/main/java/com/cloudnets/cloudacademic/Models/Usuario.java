package com.cloudnets.cloudacademic.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Creado por Deimer Villa on 06/07/2015.
 * ----------------------------------------------------
 * Clase modelo del objeto usuario:
 * Esta clase usa la libreria ORMLite para manejar la relacion
 * de la clase usuario con la tabla usuarios en la base de datos.
 * Cualquier atributo o propiedad del objeto usuario, debe ser agregado
 * aqui para que este se agregue en la base de datos.
 */
@DatabaseTable(tableName = "usuarios")
public class Usuario {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(canBeNull = false)
    private String identificacion;
    @DatabaseField(index = true, canBeNull = false)
    private String usuario;
    @DatabaseField(canBeNull = false)
    private String password;
    @DatabaseField(canBeNull = false)
    private String nombres;
    @DatabaseField(canBeNull = false)
    private String apellidos;
    @DatabaseField(index = true, canBeNull = false)
    private String email;
    @DatabaseField(canBeNull = false)
    private String perfil;
    @DatabaseField(canBeNull = false)
    private String tipoUsuario;

    //Constructor vacio para el reflejo del modelo en la api ORMLite
    public Usuario(){}

    public Usuario(String identificacion, String usuario, String password, String nombres,
                   String apellidos, String email, String perfil, String tipoUsuario) {
        this.identificacion = identificacion;
        this.usuario = usuario;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.perfil = perfil;
        this.tipoUsuario = tipoUsuario;
    }

    //Getters del modelo usuario
    public int getId() {
        return id;
    }
    public String getIdentificacion() {
        return identificacion;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getPassword() {
        return password;
    }
    public String getNombres() {
        return nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getEmail() {
        return email;
    }
    public String getPerfil() {
        return perfil;
    }
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    //Setters del modelo usuario
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}

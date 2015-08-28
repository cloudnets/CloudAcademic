package com.cloudnets.cloudacademic.Models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Creado por Deimer Villa on 03/08/2015.
 * ----------------------------------------------------
 * Clase modelo del objeto perfil:
 * Esta clase usa la libreria ORMLite para manejar la relacion
 * de la clase perfil con la tabla perfiles en la base de datos.
 * Cualquier atributo o propiedad del objeto perfil, debe ser agregado
 * aqui para que este se agregue en la base de datos.
 */
@DatabaseTable(tableName = "perfiles")
public class Perfil {

    @DatabaseField(generatedId = true)
    private int id;//LLave primaria

    @DatabaseField(canBeNull = false)
    private String identificacion;
    @DatabaseField(index = true, canBeNull = false)
    private String usuario;
    @DatabaseField(canBeNull = false)
    private String pass;
    @DatabaseField(canBeNull = false)
    private String nombres;
    @DatabaseField(canBeNull = false)
    private String apellidos;
    @DatabaseField(index = true, canBeNull = true)
    private String email;
    @DatabaseField(canBeNull = false)
    private String perfil;
    @DatabaseField(canBeNull = false)
    private String tipoUsuario;
    @DatabaseField(canBeNull = true)
    private String token;
    @DatabaseField(canBeNull = false)
    private boolean success;

    //Constructor de la entidad perfil
    public Perfil(){}

    //Constructor del objeto para los datos de inicializacion
    public Perfil(String identificacion, String usuario, String pass, String nombres,
                  String apellidos, String email, String perfil, String tipoUsuario, String token, boolean success) {
        this.identificacion = identificacion;
        this.usuario = usuario;
        this.pass = pass;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.perfil = perfil;
        this.tipoUsuario = tipoUsuario;
        this.token = token;
        this.success = success;
    }

    //Getters del modelos perfil
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
        return pass;
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
    public String getToken() {
        return token;
    }
    public boolean isSuccess() {
        return success;
    }

    //Setters del objeto perfil
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setPassword(String pass) {
        this.pass = pass;
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
    public void setToken(String token) {
        this.token = token;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}

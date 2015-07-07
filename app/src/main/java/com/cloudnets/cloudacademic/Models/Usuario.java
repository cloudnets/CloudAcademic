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

    @DatabaseField(index = true, canBeNull = false)
    private String usuario;
    @DatabaseField(canBeNull = false)
    private String password;
    @DatabaseField(canBeNull = false)
    private String identificacion;
    @DatabaseField(canBeNull = false)
    private String nombres;
    @DatabaseField(canBeNull = false)
    private String apellidos;
    @DatabaseField(index = true, canBeNull = false)
    private String email;
    @DatabaseField(canBeNull = false)
    private String cod_perfil;
    @DatabaseField(canBeNull = false)
    private String cod_tipoUsuario;
    @DatabaseField(canBeNull = false)
    private String nom_tipoUsuario;

    //Constructor vacio para el reflejo del modelo en la api ORMLite
    public Usuario(){}

    public Usuario(String usuario, String password, String identificacion, String nombres, String apellidos, String email, String cod_perfil, String cod_tipoUsuario, String nom_tipoUsuario) {
        this.setUsuario(usuario);
        this.setPassword(password);
        this.setIdentificacion(identificacion);
        this.setNombres(nombres);
        this.setApellidos(apellidos);
        this.setEmail(email);
        this.setCod_perfil(cod_perfil);
        this.setCod_tipoUsuario(cod_tipoUsuario);
        this.setNom_tipoUsuario(nom_tipoUsuario);
    }

    //Getters del modelo usuario
    public int getId() {
        return id;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getPassword() {
        return password;
    }
    public String getIdentificacion() {
        return identificacion;
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
    public String getCod_perfil() {
        return cod_perfil;
    }
    public String getCod_tipoUsuario() {
        return cod_tipoUsuario;
    }
    public String getNom_tipoUsuario() {
        return nom_tipoUsuario;
    }

    //Setters del modelo usuario
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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
    public void setCod_perfil(String cod_perfil) {
        this.cod_perfil = cod_perfil;
    }
    public void setCod_tipoUsuario(String cod_tipoUsuario) {
        this.cod_tipoUsuario = cod_tipoUsuario;
    }
    public void setNom_tipoUsuario(String nom_tipoUsuario) {
        this.nom_tipoUsuario = nom_tipoUsuario;
    }

    //Detalle del usuario
    @Override
    public String toString() {
        return "Usuario{" +
                    "usuario='" + usuario + '\'' +
                    ", password='" + password + '\'' +
                    ", identificacion='" + identificacion + '\'' +
                    ", nombres=" + nombres +
                    ", apellidos='" + apellidos + '\'' +
                    ", email='" + email + '\'' +
                    ", cod_perfil='" + cod_perfil + '\'' +
                    ", cod_tipoUsuario='" + cod_tipoUsuario + '\'' +
                    ", nom_tipoUsuario='" + nom_tipoUsuario + '\'' +
                '}';
    }

}

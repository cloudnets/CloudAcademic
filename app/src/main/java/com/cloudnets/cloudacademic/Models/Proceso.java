package com.cloudnets.cloudacademic.Models;

/**
 * Creado por Deimer Villa on 03/08/2015.
 */
public class Proceso {

/*************Atributos del objeto resultado*************/
    String title = "";
    String message = "";
    boolean success = false;

/*********Getters del objeto resultado*********/
    public String getTitle() {
        return title;
    }
    public String getMessage() {
        return message;
    }
    public boolean isSuccess() {
        return success;
    }

/*********Setters del objeto resultado*********/
    public void setTitle(String title) {
        this.title = title;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}

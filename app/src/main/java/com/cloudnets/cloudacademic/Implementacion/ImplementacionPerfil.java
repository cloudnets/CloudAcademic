package com.cloudnets.cloudacademic.Implementacion;

import com.cloudnets.cloudacademic.Models.Perfil;

/**
 * Creado por Deimer Villa on 03/08/2015.
 */
public class ImplementacionPerfil {

    //Funcion que devuelve los detalles del usuario en formato string
    public String mostrarDetalles(Perfil perfil) {
        return "Perfil{" +
                "Username='" + perfil.getUsuario() + '\'' +
                ", Identificacion='" + perfil.getIdentificacion() + '\'' +
                ", Nombre completo='" + nombreCompleto(perfil) + '\'' +
                ", Email='" + perfil.getEmail() + '\'' +
                ", Perfil='" + perfil.getPerfil() + '\'' +
                ", Tipo usuario='" + perfil.getTipoUsuario() + '\'' +
                ", success='" + perfil.isSuccess() + '\'' +
                '}';
    }

    //Funcion que devuelve el nombre completo del usuario
    public String nombreCompleto(Perfil perfil){
        return perfil.getNombres()+' '+perfil.getApellidos();
    }

    //Funcion que valida si la contrasena ingresada es la correcta
    public Boolean validarPassword(Perfil perfil, String pass){
        return pass.equalsIgnoreCase(perfil.getPassword());
    }

}

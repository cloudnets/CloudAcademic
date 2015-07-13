package com.cloudnets.cloudacademic.Implementacion;

import com.cloudnets.cloudacademic.Models.Usuario;

/**
 * Creado por Deimer Villa on 13/07/2015.
 */
public class ImplementacionUsuario {

    //Funcion que devuelve los detalles del usuario en formato string
    public String mostrarDetalles(Usuario usuario) {
        return "Usuario{" +
                "Username='" + usuario.getUsuario() + '\'' +
                ", Identificacion='" + usuario.getIdentificacion() + '\'' +
                ", Nombre completo='" + nombreCompleto(usuario) + '\'' +
                ", Email='" + usuario.getEmail() + '\'' +
                ", Perfil='" + usuario.getPerfil() + '\'' +
                ", Tipo usuario='" + usuario.getTipoUsuario() + '\'' +
                '}';
    }

    //Funcion que devuelve el nombre completo del usuario
    public String nombreCompleto(Usuario usuario){
        return usuario.getNombres()+' '+usuario.getApellidos();
    }

    //Funcion que valida si la contrasena ingresada es la correcta
    public Boolean validarPassword(Usuario usuario, String pass){
        if(pass.equalsIgnoreCase(usuario.getPassword())){
            return true;
        }else{
            return false;
        }
    }

}

package com.cloudnets.cloudacademic.Implementacion;

import com.cloudnets.cloudacademic.Models.Estudiante;

/**
 * Creado por Deimer Villa on 13/07/2015.
 * -----------------------------------------------
 * Clase encargada de las operaciones basicas para
 * el manejo de datos y formatos de informacion del
 * modelo estudiante
 */
public class ImplementacionEstudiante {

    //Detalles del estudiante
    public String showDetails(Estudiante estudiante) {
        return "Estudiante {" + '\'' +
                "Identificacion='" + estudiante.getIdentificacion() + '\'' +
                ", Tipo id='" + estudiante.getTipo_id() + '\'' +
                ", Nombre completo='" + fullName(estudiante) + '\'' +
                ", Fecha nacimiento=" + estudiante.getFecha_nacimiento() +
                ", Genero='" + estudiante.getGenero() + '\'' +
                ", Direccion='" + estudiante.getDireccion() + '\'' +
                ", Telefono='" + estudiante.getTelefono() + '\'' +
                ", Celular='" + estudiante.getCelular() + '\'' +
                ", Estrato='" + estudiante.getEstrato() + '\'' +
                ", EPS='" + estudiante.getEps() + '\'' +
                ", Grado='" + estudiante.getNom_grado() + '\'' +
                ", Codigo del grado='" + estudiante.getCod_grado() + '\'' +
                ", Codigo GradoAsig='" + estudiante.getCodgradosasig() + '\'' +
                ", Codigo EstuMatricula='" + estudiante.getCodestumatricula() + '\'' +
                '}';
    }

    //Nombre completo del estudiante
    public String fullName(Estudiante estudiante){
        return estudiante.getPrimer_nombre()+' '+
                estudiante.getSegundo_nombre()+' '+
                estudiante.getPrimer_apellido()+' '+
                estudiante.getSegundo_apellido();
    }

}

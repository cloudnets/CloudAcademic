package com.cloudnets.cloudacademic.Implementacion;

import com.cloudnets.cloudacademic.Models.Docente;

/**
 * Creado por Deimer Villa on 27/08/2015.
 * -----------------------------------------------
 * Clase encargada de las operaciones basicas para
 * el manejo de datos y formatos de informacion del
 * modelo docente
 */
public class ImplementacionDocente {

    //Detalles del docente
    public String showDetails(Docente docente) {
        return "Docente {" + '\'' +
            "Identificacion='" + docente.getIdentificacion() + '\'' +
            ", Telefono= '" + docente.getTelefono() + '\'' +
            ", Celular='" + docente.getCelular() + '\'' +
            ", Direccion=" + docente.getDireccion() +
            ", Fecha Nacimiento='" + docente.getFechanacimiento() + '\'' +
            ", Email='" + docente.getEmail() + '\'' +
            ", Genero='" + docente.getGenero() + '\'' +
            ", Fecha Vinculacion='" + docente.getFechavinculacion() + '\'' +
            ", Estado='" + docente.getEstado() + '\'' +
            ", Nombre Completo='" + docente.getFullname() + '\'' +
        '}';
    }

}

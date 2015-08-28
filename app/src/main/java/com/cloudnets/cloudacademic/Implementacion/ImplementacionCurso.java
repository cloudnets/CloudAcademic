package com.cloudnets.cloudacademic.Implementacion;

import com.cloudnets.cloudacademic.Models.Curso;

/**
 * Creado por Deimer Villa on 27/08/2015.
 * -----------------------------------------------
 * Clase encargada de las operaciones basicas para
 * el manejo de datos y formatos de informacion del
 * modelo curso
 */
public class ImplementacionCurso {

    //Detalles del curso
    public String showDetails(Curso curso) {
        return "Curso {" + '\'' +
            "Codigo='" + curso.getCodigo() + '\'' +
            ", Descripcion= '" + curso.getDescripcion() + '\'' +
            ", Intensidad Horaria='" + curso.getInthoraria() + '\'' +
        '}';
    }

}

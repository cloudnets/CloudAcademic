package com.cloudnets.cloudacademic.Implementacion;

import com.cloudnets.cloudacademic.Models.Asignatura;

/**
 * Creado por Deimer Villa on 27/08/2015.
 * -----------------------------------------------
 * Clase encargada de las operaciones basicas para
 * el manejo de datos y formatos de informacion del
 * modelo asignatura
 */
public class ImplementacionAsignatura {

    //Detalles del curso
    public String showDetails(Asignatura asignatura) {
        return "Asignatura {" + '\'' +
                "Codigo='" + asignatura.getCodasignatura() + '\'' +
                ", Descripcion= '" + asignatura.getNombre() + '\'' +
                ", Abreviatura='" + asignatura.getAbreviatura() + '\'' +
                ", Curso='" + asignatura.getCurso() + '\'' +
                ", Intensidad Horaria='" + asignatura.getIntensidad_horaria() + '\'' +
                ", Procentaje='" + asignatura.getPorcentaje() + '\'' +
                '}';
    }

}

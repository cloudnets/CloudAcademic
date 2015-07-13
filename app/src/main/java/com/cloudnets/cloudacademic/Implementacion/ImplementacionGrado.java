package com.cloudnets.cloudacademic.Implementacion;

import com.cloudnets.cloudacademic.Models.Grado;

/**
 * Creado por Deimer Villa on 13/07/2015.
 */
public class ImplementacionGrado {

    //Funcion para mostrar los detalles de cada grado
    public String mostrarDetalles(Grado grado) {
        return "Grado{" +
                "Nombre='" + grado.getNombre() + '\'' +
                ", Codigo='" + grado.getCodigo() + '\'' +
                ", Cod_Docente='" + grado.getCod_docente() + '\'' +
                '}';
    }

}

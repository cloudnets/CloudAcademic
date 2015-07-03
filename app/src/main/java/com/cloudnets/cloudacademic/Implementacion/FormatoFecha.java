package com.cloudnets.cloudacademic.Implementacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Deimer on 01/07/2015.
 * --------------------------------------------
 * Clase encargada de las operaciones en el tema
 * de las fechas y sus formatos.
 */
public class FormatoFecha {

    public Date formatoFecha(String fechaEntrada){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(fechaEntrada);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String obtenerFechaActual(){
        String fechaActual = new SimpleDateFormat( "yyyy-MM-dd_HH:mm:ss", java.util.Locale.getDefault()).
                format(Calendar.getInstance().getTime());
        System.out.println(fechaActual);
        return fechaActual;
    }

    public int diferenciaEnDias(String fechaMayor, String fechaMenor){
        Date mayor = formatoFecha(fechaMayor);
        Date menor = formatoFecha(fechaMenor);
        System.out.println("ultima fecha: "+mayor+"; fecha actual: "+menor);
        long diferencia = (mayor.getTime()) - (menor.getTime());
        System.out.println("ultima fecha tiempo: "+mayor.getTime()+"; fecha actual tiempo: "+menor.getTime());
        long dias = diferencia / (24 * 60 * 60 * 1000);
        if(dias < 0){
            dias = dias*-1;
        }
        return (int) dias;
    }

}

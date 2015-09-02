package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;
import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Models.Inasistencia;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import java.util.ArrayList;
import java.util.List;

/**
 * Creado por Deimer Villa on 31/08/2015.
 * ----------------------------------------------------
 * Esta clase es la encargada de manejar las funciones
 * de la logica de los datos del modelo (Inasistencia)
 * que se tiene que llevar a cabo por la aplicacion.
 */
public class InasistenciaController /*Clase::Controller*/{

    //Variable que permite traer las funciones de la base de datos
    private DatabaseHelper dbHelper;

    //Funcion que permite la creacion de una inasistencia nuevo
    public boolean crear(Inasistencia asignatura,Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Inasistencia, Integer> inasistenciaDao = dbHelper.getInasistenciaRuntimeDao();
            inasistenciaDao.create(asignatura);
        } catch (Exception ex) {
            res = false;
            Log.e("InasistenciaController(crear)", "Error: " + ex.getMessage());
        }
        return res;
    }

    //Funcion que permite la busqueda de un estudiante mediante su id
    public Inasistencia detalle(int id, Context context){
        Inasistencia inasistencia;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Inasistencia, Integer> inasistenciaDao = dbHelper.getInasistenciaRuntimeDao();
            inasistencia = inasistenciaDao.queryForId(id);
        } catch (Exception ex) {
            inasistencia = null;
            Log.e("InasistenciaController(detalle)", "Error: " + ex.toString());
        }
        return inasistencia;
    }

    public List<Inasistencia> obtenerInasistencias(Context context){
        List<Inasistencia> lista = new ArrayList<>();
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Inasistencia, Integer> inasistenciaDao = dbHelper.getInasistenciaRuntimeDao();
            lista = inasistenciaDao.queryForAll();
        }catch (Exception ex){
            Log.e("InasistenciaController(obtenerReportes)", "Error: " + ex.toString());
        }
        return lista;
    }

}

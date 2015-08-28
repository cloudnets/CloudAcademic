package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;
import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Models.Asignatura;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

/**
 * Creado por Deimer Villa on 24/08/2015.
 * ----------------------------------------------------
 * Esta clase es la encargada de manejar las funciones
 * de la logica de los datos del modelo (Asignatura)
 * que se tiene que llevar a cabo por la aplicacion.
 */
public class AsignaturaController {

    //Variable que permite traer las funciones de la base de datos
    private DatabaseHelper dbHelper;

    //Funcion que permite la creacion de una asignatura nuevo
    public boolean crear(Asignatura asignatura,Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Asignatura, Integer> asignaturaDao = dbHelper.getAsignaturaRuntimeDao();
            asignaturaDao.create(asignatura);
        } catch (Exception ex) {
            res = false;
            Log.e("AsignaturaController(crear)", "Error: " + ex.getMessage());
        }
        return res;
    }

    //Funcion que permite la busqueda de un estudiante mediante su id
    public Asignatura detalle(int id, Context context){
        Asignatura asignatura;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Asignatura, Integer> asignaturaDao = dbHelper.getAsignaturaRuntimeDao();
            asignatura = asignaturaDao.queryForId(id);
        } catch (Exception ex) {
            asignatura = null;
            Log.e("EstudianteController(detalle)", "Error: " + ex.toString());
        }
        return asignatura;
    }

}

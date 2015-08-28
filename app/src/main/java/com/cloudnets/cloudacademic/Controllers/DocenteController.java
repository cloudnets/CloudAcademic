package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;
import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Models.Docente;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

/**
 * Creado por Deimer Villa on 24/08/2015.
 */
public class DocenteController {

    //Variable que permite traer las funciones de la base de datos
    private DatabaseHelper dbHelper;

    //Funcion que permite la creacion de un estudiante nuevo
    public boolean crear(Docente docente,Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Docente, Integer> docenteDao = dbHelper.getDocenteRuntimeDao();
            docenteDao.create(docente);
        } catch (Exception ex) {
            res = false;
            Log.e("DocenteController(crear)", "Error: " + ex.getMessage());
        }
        return res;
    }

    //Funcion que permite la busqueda de un docente mediante su id
    public Docente detalle(int id, Context context){
        Docente docente;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Docente, Integer> docenteDao = dbHelper.getDocenteRuntimeDao();
            docente = docenteDao.queryForId(id);
        } catch (Exception ex) {
            docente = null;
            Log.e("DocenteController(detalle)", "Error: " + ex.toString());
        }
        return docente;
    }

}

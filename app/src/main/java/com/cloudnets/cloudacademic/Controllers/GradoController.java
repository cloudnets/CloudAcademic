package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;
import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Models.Grado;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

/**
 * Creado por Deimer Villa on 10/07/2015.
 */
public class GradoController /*Clas::Controller*/ {

    //Variable que permite traer las funciones de la base de datos
    DatabaseHelper dbHelper;

    //Funcion que permite la creacion de un grado nuevo en la base de datos
    public boolean crear(Grado grado, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Grado, Integer> gradoDao = dbHelper.getGradoRuntimeDao();
            gradoDao.create(grado);
        }catch (Exception ex){
            res = false;
            Log.e("GradoController(crear)", "Error: " + ex.toString());
        }
        return res;
    }

    public boolean actualizar(Grado grado, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Grado, Integer> gradoDao = dbHelper.getGradoRuntimeDao();
            gradoDao.update(grado);
        } catch (Exception e) {
            res = false;
            Log.e("GradoController(actualizar)", "Error: " + e.toString());
        }
        return res;
    }

    public boolean eliminar(Grado grado, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Grado, Integer> gradoDao = dbHelper.getGradoRuntimeDao();
            gradoDao.delete(grado);
        } catch (Exception e) {
            res = false;
            Log.e("GradoController(eliminar)", "Error: " + e.toString());
        }
        return res;
    }

    public Grado detalle(int id, Context context){
        Grado grado;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Grado, Integer> gradoDao = dbHelper.getGradoRuntimeDao();
            grado = gradoDao.queryForId(id);
        } catch (Exception e) {
            grado = null;
            Log.e("GradoController(detalle)", "Error: " + e.toString());
        }
        return grado;
    }

}

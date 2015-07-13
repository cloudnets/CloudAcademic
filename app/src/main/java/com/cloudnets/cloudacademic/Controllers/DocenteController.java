package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;
import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Models.Docente;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

/**
 * Creado por Deimer Villa on 08/07/2015.
 * ----------------------------------------------------
 * Esta clase es la encargada de manejar las funciones
 * de la logica de los datos del modelo (Docente) usando consultas
 * del builder de ORMLite desde la sintaxis en java
 * que se tiene que llevar a cabo por la aplicacion.
 */
public class DocenteController /*Clase::Controller*/{

    //Variable que permite traer las funciones de la base de datos
    DatabaseHelper dbHelper;

    //Funcion que permite la creacion de un docente nuevo en la base de datos
    public boolean crear(Docente docente, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Docente, Integer> docenteDao = dbHelper.getDocenteRuntimeDao();
            docenteDao.create(docente);
        }catch (Exception ex){
            res = false;
            Log.e("DocenteController(crear)", "Error: " + ex.toString());
        }
        return res;
    }

    public boolean actualizar(Docente docente, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Docente, Integer> docenteDao = dbHelper.getDocenteRuntimeDao();
            docenteDao.update(docente);
        } catch (Exception e) {
            res = false;
            Log.e("DocenteController(actualizar)", "Error: " + e.toString());
        }
        return res;
    }

    public boolean eliminar(Docente docente, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Docente, Integer> docenteDao = dbHelper.getDocenteRuntimeDao();
            docenteDao.delete(docente);
        } catch (Exception e) {
            res = false;
            Log.e("DocenteController(eliminar)", "Error: " + e.toString());
        }
        return res;
    }

    public Docente detalle(int id, Context context){
        Docente docente;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Docente, Integer> docenteDao = dbHelper.getDocenteRuntimeDao();
            docente = docenteDao.queryForId(id);
        } catch (Exception e) {
            docente = null;
            Log.e("DocenteController(detalle)", "Error: " + e.toString());
        }
        return docente;
    }

}

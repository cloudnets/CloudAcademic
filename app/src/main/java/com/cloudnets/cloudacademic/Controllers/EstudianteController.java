package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;
import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Models.Estudiante;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado por Deimer Villa on 03/08/2015.
 * ----------------------------------------------------
 * Esta clase es la encargada de manejar las funciones
 * de la logica de los datos del modelo (Estudiante)
 * que se tiene que llevar a cabo por la aplicacion.
 */
public class EstudianteController /*Clase::Controller*/{

    //Variable que permite traer las funciones de la base de datos
    private DatabaseHelper dbHelper;

    //Funcion que permite la creacion de un estudiante nuevo
    public boolean crear(Estudiante estudiante,Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Estudiante, Integer> estudianteDao = dbHelper.getEstudianteRuntimeDao();
            estudianteDao.create(estudiante);
        } catch (Exception ex) {
            res = false;
            Log.e("EstudianteController(crear)", "Error: " + ex.getMessage());
        }
        return res;
    }

    //Funcion que permite el actualizar los datos de un estudiante
    public boolean actualizar(Estudiante estudiante, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Estudiante, Integer> estudianteDao = dbHelper.getEstudianteRuntimeDao();
            estudianteDao.update(estudiante);
        } catch (Exception ex) {
            res = false;
            Log.e("EstudianteController(actualizar)", "Error: " + ex.toString());
        }
        return res;
    }

    //Funcion que permite eliminar un estudiante de la base de datos
    public boolean eliminar(Estudiante estudiante, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Estudiante, Integer> estudianteDao = dbHelper.getEstudianteRuntimeDao();
            estudianteDao.delete(estudiante);
        } catch (Exception ex) {
            res = false;
            Log.e("EstudianteController(eliminar)", "Error: " + ex.toString());
        }
        return res;
    }

    //Funcion que permite la busqueda de un estudiante mediante su id
    public Estudiante detalle(int id, Context context){
        Estudiante estudiante;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Estudiante, Integer> estudianteDao = dbHelper.getEstudianteRuntimeDao();
            estudiante = estudianteDao.queryForId(id);
        } catch (Exception ex) {
            estudiante = null;
            Log.e("EstudianteController(detalle)", "Error: " + ex.toString());
        }
        return estudiante;
    }

    //Funcion que permite la busqueda de un estudiante mediante su id
    public List<Estudiante> listaEstudiantes(String cod_curso, Context context){
        List<Estudiante> listaE = null;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Estudiante, Integer> estudianteDao = dbHelper.getEstudianteRuntimeDao();
            QueryBuilder<Estudiante,Integer> queryBuilder = estudianteDao.queryBuilder();
            queryBuilder.where().eq("cod_curso",cod_curso);
            PreparedQuery<Estudiante> preparedQuery = queryBuilder.prepare();
            listaE = estudianteDao.query(preparedQuery);
        } catch (Exception ex) {
            Log.e("EstudianteController(detalle)", "Error: " + ex.toString());
        }
        return listaE;
    }

    public List<Estudiante> obtenerEstudiantes(Context context){
        List<Estudiante> listaE = new ArrayList<>();
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Estudiante, Integer> estudianteDao = dbHelper.getEstudianteRuntimeDao();
            listaE = estudianteDao.queryForAll();
        }catch (Exception ex){
            Log.e("EstudianteController(obtenerEstudiantes)", "Error: " + ex.toString());
        }
        return listaE;
    }

}

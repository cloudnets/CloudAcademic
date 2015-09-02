package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;

import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Models.Curso;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado por Deimer Villa on 24/08/2015.
 * ----------------------------------------------------
 * Esta clase es la encargada de manejar las funciones
 * de la logica de los datos del modelo (Curso)
 * que se tiene que llevar a cabo por la aplicacion.
 */
public class CursoController /*Clase::Controller*/{

    //Variable que permite traer las funciones de la base de datos
    private DatabaseHelper dbHelper;

    //Funcion que permite la creacion de un curso nuevo
    public boolean crear(Curso curso,Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Curso, Integer> cursoDao = dbHelper.getCursoRuntimeDao();
            cursoDao.create(curso);
        } catch (Exception ex) {
            res = false;
            Log.e("CursoController(crear)", "Error: " + ex.getMessage());
        }
        return res;
    }

    //Funcion que permite la creacion de un curso nuevo
    public boolean editar(Curso curso,Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Curso, Integer> cursoDao = dbHelper.getCursoRuntimeDao();
            cursoDao.update(curso);
        } catch (Exception ex) {
            res = false;
            Log.e("CursoController(editar)", "Error: " + ex.getMessage());
        }
        return res;
    }

    //Funcion que permite eliminar un curso de la base de datos
    public boolean eliminar(Curso curso, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Curso, Integer> cursoDao = dbHelper.getCursoRuntimeDao();
            cursoDao.delete(curso);
        } catch (Exception ex) {
            res = false;
            Log.e("CursoController(eliminar)", "Error: " + ex.toString());
        }
        return res;
    }

    //Funcion que permite la busqueda de un curso mediante su id
    public Curso detalle(int id, Context context){
        Curso curso;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Curso, Integer> cursoDao = dbHelper.getCursoRuntimeDao();
            curso = cursoDao.queryForId(id);
        } catch (Exception ex) {
            curso = null;
            Log.e("CursoController(detalle)", "Error: " + ex.toString());
        }
        return curso;
    }

    public List<Curso> obtenerCursos(Context context){
        List<Curso> lista = new ArrayList<>();
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Curso, Integer> cursoDao = dbHelper.getCursoRuntimeDao();
            lista = cursoDao.queryBuilder().distinct().selectColumns("id","descripcion").orderBy("id",true).query();
            System.out.println("Curso controller: "+lista);
        }catch (Exception ex){
            Log.e("CursoController(obtenerCursos)", "Error: " + ex.toString());
        }
        return lista;
    }

}

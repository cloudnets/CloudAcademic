package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;

import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Helpers.Funciones;
import com.cloudnets.cloudacademic.Models.Asignatura;
import com.cloudnets.cloudacademic.Models.Curso;
import com.cloudnets.cloudacademic.Models.Docente;
import com.cloudnets.cloudacademic.Models.Estudiante;
import com.cloudnets.cloudacademic.Models.Perfil;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.util.List;

/**
 * Creado por Deimer Villa on 07/07/2015.
 * ----------------------------------------------------
 * Esta clase es la encargada de manejar las funciones
 * de la logica de los datos del modelo (Usuario)
 * que se tiene que llevar a cabo por la aplicacion.
 */
public class PerfilController /*Clase::Controller*/{

    //Variable que permite traer las funciones de la base de datos
    private DatabaseHelper dbHelper;

    //Funcion que permite la creacion de un perfil nuevo
    public boolean crear(Perfil perfil,Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Perfil, Integer> perfilDao = dbHelper.getPerfilRuntimeDao();
            perfilDao.create(perfil);
        } catch (Exception ex) {
            res = false;
            Log.e("PerfilController(crear)", "Error: " + ex.getLocalizedMessage());
        }
        return res;
    }

    //Funcion que permite el actualizar los datos de un usuario
    public boolean actualizar(Perfil perfil, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Perfil, Integer> perfilDao = dbHelper.getPerfilRuntimeDao();
            perfilDao.update(perfil);
        } catch (Exception ex) {
            res = false;
            Log.e("PerfilController(actualizar)", "Error: " + ex.toString());
        }
        return res;
    }

    //Funcion que permite la busqueda de un perfil mediante su id
    public Perfil detalle(int id, Context context){
        Perfil perfil;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Perfil, Integer> perfilDao = dbHelper.getPerfilRuntimeDao();
            perfil = perfilDao.queryForId(id);
        } catch (Exception ex) {
            perfil = null;
            Log.e("PerfilController(detalle)", "Error: " + ex.toString());
        }
        return perfil;
    }

    public boolean sesionActiva(Context contexto){
        boolean res = false;
        try {
            dbHelper = OpenHelperManager.getHelper(contexto,DatabaseHelper.class);
            RuntimeExceptionDao<Perfil, Integer> perfilDao = dbHelper.getPerfilRuntimeDao();
            int cantidad = (int)perfilDao.countOf();
            if(cantidad > 0){
                res = true;
            }
        }catch(Exception ex){
            Log.e("PerfilController(sesionActiva)", "Error: " + ex.toString());
        }
        return res;
    }

    public boolean cerrarSesion(int id, Context contexto){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(contexto,DatabaseHelper.class);
            RuntimeExceptionDao<Perfil, Integer> perfilDao = dbHelper.getPerfilRuntimeDao();
            perfilDao.deleteById(id);
        }catch (Exception ex){
            res = false;
            Log.e("PerfilController(cerrarSesion)", "Error: " + ex.toString());
        }
        return res;
    }

    public Perfil buscarPerfil(Context contexto){
        Perfil perfil = null;
        try {
            dbHelper = OpenHelperManager.getHelper(contexto,DatabaseHelper.class);
            RuntimeExceptionDao<Perfil, Integer> perfilDao = dbHelper.getPerfilRuntimeDao();
            QueryBuilder<Perfil, Integer> builder = perfilDao.queryBuilder();
            builder.limit(1);
            List<Perfil> lista = perfilDao.query(builder.prepare());
            if(!lista.isEmpty()){
                perfil = lista.get(0);
            }
        }catch (Exception ex){
            Log.e("PerfilController(buscarPerfil)", "Error: " + ex.toString());
        }
        return perfil;
    }

}

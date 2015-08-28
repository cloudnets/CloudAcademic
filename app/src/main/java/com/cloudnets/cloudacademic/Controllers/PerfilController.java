package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;

import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Helpers.Funciones;
import com.cloudnets.cloudacademic.Models.Perfil;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.UpdateBuilder;

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

    //Funcion que permite eliminar un usuario de la base de datos
    public boolean eliminar(Perfil perfil, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Perfil, Integer> perfilDao = dbHelper.getPerfilRuntimeDao();
            perfilDao.delete(perfil);
        } catch (Exception ex) {
            res = false;
            Log.e("PerfilController(eliminar)", "Error: " + ex.toString());
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
            int cantidad = (int)perfilDao.queryBuilder().where().ne("token", "null").countOf();
            if(cantidad > 0){
                res = true;
            }
        }catch(Exception ex){
            Log.e("PerfilController(sesionActiva)", "Error: " + ex.toString());
        }
        return res;
    }

    public boolean validarUser(Context contexto, String user, String pass){
        boolean res = false;
        try {
            dbHelper = OpenHelperManager.getHelper(contexto,DatabaseHelper.class);
            RuntimeExceptionDao<Perfil, Integer> perfilDao = dbHelper.getPerfilRuntimeDao();
            int cantidad = (int)perfilDao.queryBuilder().where().eq("usuario",user).and().eq("pass",pass).countOf();
            if (cantidad > 0){
                activarSesionUsuario(user,pass, contexto);
                res = true;
            }
        }catch (Exception ex){
            Log.e("PerfilController(validarUser)", "Error: " + ex.toString());
        }
        return res;
    }

    public void activarSesionUsuario(String user, String pass, Context contexto){
        Funciones funciones = new Funciones(contexto);
        String newToken = funciones.generarToken();
        try {
            dbHelper = OpenHelperManager.getHelper(contexto,DatabaseHelper.class);
            RuntimeExceptionDao<Perfil, Integer> perfilDao = dbHelper.getPerfilRuntimeDao();
            UpdateBuilder<Perfil,Integer> updateBuilder = perfilDao.updateBuilder();
            updateBuilder.where().eq("usuario",user).and().eq("pass",pass);
            updateBuilder.updateColumnValue("token", newToken);
            updateBuilder.update();
        }catch (Exception ex){
            Log.e("PerfilController(activarSesionUsuario)", "Error: " + ex.toString());
        }
    }

    public boolean cerrarSesion(Context contexto){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(contexto,DatabaseHelper.class);
            RuntimeExceptionDao<Perfil, Integer> perfilDao = dbHelper.getPerfilRuntimeDao();
            UpdateBuilder<Perfil,Integer> updateBuilder = perfilDao.updateBuilder();
            updateBuilder.where().ne("token",null);
            updateBuilder.updateColumnValue("token",null);
            updateBuilder.update();
        }catch (Exception ex){
            res = false;
            Log.e("PerfilController(cerrarSesion)", "Error: " + ex.toString());
        }
        return res;
    }

}

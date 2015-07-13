package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;
import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Models.Usuario;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

/**
 * Creado por Deimer Villa on 07/07/2015.
 * ----------------------------------------------------
 * Esta clase es la encargada de manejar las funciones
 * de la logica de los datos del modelo (Usuario)
 * que se tiene que llevar a cabo por la aplicacion.
 */
public class UsuarioController /*Clase::Controller*/{

    //Variable que permite traer las funciones de la base de datos
    private DatabaseHelper dbHelper;

    //Funcion que permite la creacion de un usuario nuevo
    public boolean crear(Usuario usuario,Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Usuario, Integer> usuarioDao = dbHelper.getUsuarioRuntimeDao();
            usuarioDao.create(usuario);
        } catch (Exception ex) {
            res = false;
            Log.e("UsuarioController(crear)", "Error: " + ex.getMessage().toString());
        }
        return res;
    }

    //Funcion que permite el actualizar los datos de un usuario
    public boolean actualizar(Usuario usuario, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Usuario, Integer> usuarioDao = dbHelper.getUsuarioRuntimeDao();
            usuarioDao.update(usuario);
        } catch (Exception ex) {
            res = false;
            Log.e("UsuarioController(actualizar)", "Error: " + ex.toString());
        }
        return res;
    }

    //Funcion que permite eliminar un usuario de la base de datos
    public boolean eliminar(Usuario usuario, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Usuario, Integer> usuarioDao = dbHelper.getUsuarioRuntimeDao();
            usuarioDao.delete(usuario);
        } catch (Exception ex) {
            res = false;
            Log.e("UsuarioController(eliminar)", "Error: " + ex.toString());
        }
        return res;
    }

    //Funcion que permite la busqueda de un usuario mediante su id
    public Usuario detalle(int id, Context context){
        Usuario usuario;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Usuario, Integer> usuarioDao = dbHelper.getUsuarioRuntimeDao();
            usuario = usuarioDao.queryForId(id);
        } catch (Exception ex) {
            usuario = null;
            Log.e("UsuarioController(detalle)", "Error: " + ex.toString());
        }
        return usuario;
    }

    /**/

}

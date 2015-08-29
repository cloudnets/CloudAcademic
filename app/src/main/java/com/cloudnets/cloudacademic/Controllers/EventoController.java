package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;

import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Models.Evento;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado por Deimer Villa on 29/08/2015.
 * ----------------------------------------------------
 * Esta clase es la encargada de manejar las funciones
 * de la logica de los datos del modelo (Evento)
 * que se tiene que llevar a cabo por la aplicacion.
 */
public class EventoController /*Clase::Controller*/{

    //Variable que permite traer las funciones de la base de datos
    private DatabaseHelper dbHelper;

    //Funcion que permite la creacion de un evento nuevo
    public boolean crear(Evento evento,Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Evento, Integer> eventoDao = dbHelper.getEventoRuntimeDao();
            eventoDao.create(evento);
        } catch (Exception ex) {
            res = false;
            Log.e("EventoController(crear)", "Error: " + ex.getLocalizedMessage());
        }
        return res;
    }

    //Funcion que permite el actualizar los datos de un usuario
    public boolean actualizar(Evento evento, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Evento, Integer> eventoDao = dbHelper.getEventoRuntimeDao();
            eventoDao.update(evento);
        } catch (Exception ex) {
            res = false;
            Log.e("EventoController(actualizar)", "Error: " + ex.toString());
        }
        return res;
    }

    //Funcion que permite eliminar un evento de la base de datos
    public boolean eliminar(Evento evento, Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Evento, Integer> eventoDao = dbHelper.getEventoRuntimeDao();
            eventoDao.delete(evento);
        } catch (Exception ex) {
            res = false;
            Log.e("EventoController(eliminar)", "Error: " + ex.toString());
        }
        return res;
    }

    //Funcion que permite la busqueda de un perfil mediante su id
    public Evento detalle(int id, Context context){
        Evento evento;
        try {
            dbHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
            RuntimeExceptionDao<Evento, Integer> eventoDao = dbHelper.getEventoRuntimeDao();
            evento = eventoDao.queryForId(id);
        } catch (Exception ex) {
            evento = null;
            Log.e("EventoController(detalle)", "Error: " + ex.toString());
        }
        return evento;
    }

    public List<Evento> obtenerEventos(Context context){
        List<Evento> lista = new ArrayList<>();
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<Evento, Integer> eventoDao = dbHelper.getEventoRuntimeDao();
            lista = eventoDao.queryForAll();
        }catch (Exception ex){
            Log.e("EventoController(obtenerEventos)", "Error: " + ex.toString());
        }
        return lista;
    }

}

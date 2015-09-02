package com.cloudnets.cloudacademic.Controllers;

import android.content.Context;
import android.util.Log;
import com.cloudnets.cloudacademic.Database.DatabaseHelper;
import com.cloudnets.cloudacademic.Models.ReporteInasistencia;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import java.util.ArrayList;
import java.util.List;

/**
 * Creado por Deimer Villa on 31/08/2015.
 */
public class ReporteController {

    //Variable que permite traer las funciones de la base de datos
    private DatabaseHelper dbHelper;

    //Funcion que permite la creacion de un curso nuevo
    public boolean crear(ReporteInasistencia reporte,Context context){
        boolean res = true;
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<ReporteInasistencia, Integer> reporteDao = dbHelper.getReporteRuntimeDao();
            reporteDao.create(reporte);
        } catch (Exception ex) {
            res = false;
            Log.e("ReporteController(crear)", "Error: " + ex.getMessage());
        }
        return res;
    }

    public List<ReporteInasistencia> obtenerReportes(Context context){
        List<ReporteInasistencia> lista = new ArrayList<>();
        try {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
            RuntimeExceptionDao<ReporteInasistencia, Integer> reporteDao = dbHelper.getReporteRuntimeDao();
            lista = reporteDao.queryForAll();
        }catch (Exception ex){
            Log.e("ReporteController(obtenerReportes)", "Error: " + ex.toString());
        }
        return lista;
    }

}

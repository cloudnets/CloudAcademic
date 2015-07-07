package com.cloudnets.cloudacademic.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.cloudnets.cloudacademic.Models.Docente;
import com.cloudnets.cloudacademic.Models.Estudiante;
import com.cloudnets.cloudacademic.Models.Grado;
import com.cloudnets.cloudacademic.Models.Usuario;
import com.cloudnets.cloudacademic.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/**
 * Creado por Deimer Villa on 06/07/2015.
 * ----------------------------------------------------
 * Esta clase es la encargada de administrar la creacion y actualizacion
 * la base de datos usando el patron de disenios DAO (Data Access Object).
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    //Variables que manejaran la informacion estadar de la base de datos
    private static final String DATABASE_NAME = "cloudacademic.db";
    private static final int DATABASE_VERSION = 1;
    //Variables manejadoras de las funciones de la libreria ORMLite
    private Dao<Usuario, Integer> usuarioDAO = null;
    private Dao<Docente, Integer> docenteDAO = null;
    private Dao<Estudiante, Integer> estudianteDAO = null;
    private Dao<Grado, Integer> gradoDAO = null;
    private RuntimeExceptionDao<Usuario, Integer> usuarioRuntimeDao = null;
    private RuntimeExceptionDao<Docente, Integer> docenteRuntimeDao = null;
    private RuntimeExceptionDao<Estudiante, Integer> estudianteRuntimeDao = null;
    private RuntimeExceptionDao<Grado, Integer> gradoRuntimeDao = null;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource source) {
        try {
            TableUtils.createTable(source, Usuario.class);
            TableUtils.createTable(source, Docente.class);
            TableUtils.createTable(source, Estudiante.class);
            TableUtils.createTable(source, Grado.class);
        }catch (SQLException sqlEx){
            Log.e("DatabaseHelper(onCreate)","Error:" + sqlEx);
            throw new RuntimeException(sqlEx);
        }
    }

    /*Funcion que permite actualizar la base de datos cuando sea necesario
    * Usa como parametros;
    * @param db -> extension de la base de datos
    * @param source -> variable para la conexion a la base de datos
    * @param oldVersion -> numero de version actual de la base de datos
    * @param newVersion -> numero de la nueva version de la base de datos
    */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource source, int oldVersion, int newVersion){
        try {
            TableUtils.dropTable(source, Usuario.class, true);
            TableUtils.dropTable(source, Docente.class, true);
            TableUtils.dropTable(source, Estudiante.class, true);
            TableUtils.dropTable(source, Grado.class, true);
            onCreate(db, source);
        }catch (SQLException sqlEx){
            Log.e("DatabaseHelper(onUpgrade)", "Error: " + sqlEx);
            throw new RuntimeException(sqlEx);
        }
    }

    /*Funcion que permite cerrar la conexion a la base de datos.
    * No usa parametros
    */
    @Override
    public void close(){
        super.close();
        usuarioDAO = null;
        docenteDAO = null;
        estudianteDAO = null;
        gradoDAO = null;
        usuarioRuntimeDao = null;
        docenteRuntimeDao = null;
        estudianteRuntimeDao = null;
        gradoRuntimeDao = null;
    }

    //Getters de los modelos encargados de obtener los datos
    public Dao<Usuario, Integer> getUsuarioDAO() throws SQLException{
        if(usuarioDAO==null) usuarioDAO = getDao(Usuario.class);
        return usuarioDAO;
    }
    public Dao<Docente, Integer> getDocenteDAO() throws SQLException{
        if(docenteDAO==null) docenteDAO = getDao(Docente.class);
        return docenteDAO;
    }
    public Dao<Estudiante, Integer> getEstudianteDAO() throws SQLException{
        if(estudianteDAO==null) estudianteDAO = getDao(Estudiante.class);
        return estudianteDAO;
    }
    public Dao<Grado, Integer> getGradoDAO() throws SQLException{
        if(gradoDAO==null) gradoDAO = getDao(Grado.class);
        return gradoDAO;
    }

    //Getters de los modelos encargados de manipulas los datos con excepciones de SQL
    public RuntimeExceptionDao<Usuario, Integer> getUsuarioRuntimeDao() {
        if(usuarioRuntimeDao==null) usuarioRuntimeDao = getRuntimeExceptionDao(Usuario.class);
        return usuarioRuntimeDao;
    }
    public RuntimeExceptionDao<Docente, Integer> getDocenteRuntimeDao() {
        if(docenteRuntimeDao==null) docenteRuntimeDao = getRuntimeExceptionDao(Docente.class);
        return docenteRuntimeDao;
    }
    public RuntimeExceptionDao<Estudiante, Integer> getEstudianteRuntimeDao() {
        if(estudianteRuntimeDao==null) estudianteRuntimeDao = getRuntimeExceptionDao(Estudiante.class);
        return estudianteRuntimeDao;
    }
    public RuntimeExceptionDao<Grado, Integer> getGradoRuntimeDao() {
        if(gradoRuntimeDao==null) gradoRuntimeDao = getRuntimeExceptionDao(Grado.class);
        return gradoRuntimeDao;
    }

}
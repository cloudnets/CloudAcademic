package com.cloudnets.cloudacademic.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cloudnets.cloudacademic.Models.Asignatura;
import com.cloudnets.cloudacademic.Models.Curso;
import com.cloudnets.cloudacademic.Models.Docente;
import com.cloudnets.cloudacademic.Models.Estudiante;
import com.cloudnets.cloudacademic.Models.Evento;
import com.cloudnets.cloudacademic.Models.Perfil;
import com.cloudnets.cloudacademic.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

/**
 * Creado por Deimer Villa on 03/08/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

    //Variables que manejaran la informacion estadar de la base de datos
    private static final String DATABASE_NAME = "cloudacademic.db";
    private static final int DATABASE_VERSION = 1;
    //Variables manejadoras de las funciones de la libreria ORMLite
    private Dao<Perfil, Integer> perfilDao = null;
    private RuntimeExceptionDao<Perfil, Integer> perfilRuntimeDao = null;
    private Dao<Docente, Integer> docenteDao = null;
    private RuntimeExceptionDao<Docente, Integer> docenteRuntimeDao = null;
    private Dao<Estudiante, Integer> estudianteDao = null;
    private RuntimeExceptionDao<Estudiante, Integer> estudianteRuntimeDao = null;
    private Dao<Curso, Integer> cursoDao = null;
    private RuntimeExceptionDao<Curso, Integer> cursoRuntimeDao = null;
    private Dao<Asignatura, Integer> asignaturaDao = null;
    private RuntimeExceptionDao<Asignatura, Integer> asignaturaRuntimeDao = null;
    private Dao<Evento, Integer> eventoDao = null;
    private RuntimeExceptionDao<Evento, Integer> eventoRuntimeDao = null;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource source) {
        try {
            TableUtils.createTable(source, Perfil.class);
            TableUtils.createTable(source, Docente.class);
            TableUtils.createTable(source, Estudiante.class);
            TableUtils.createTable(source, Curso.class);
            TableUtils.createTable(source, Asignatura.class);
            TableUtils.createTable(source, Evento.class);
        }catch(SQLException sqlEx){
            Log.e("DatabaseHelper(onCreate)", "Error:" + sqlEx);
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
    public void onUpgrade(SQLiteDatabase db, ConnectionSource source, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(source, Perfil.class, true);
            TableUtils.dropTable(source, Docente.class, true);
            TableUtils.dropTable(source, Estudiante.class, true);
            TableUtils.dropTable(source, Curso.class, true);
            TableUtils.dropTable(source, Asignatura.class, true);
            TableUtils.dropTable(source, Evento.class, true);
            onCreate(db, source);
        }catch (SQLException sqlEx){
            Log.e("DatabaseHelper(onUpgrade)", "Error: " + sqlEx);
            throw new RuntimeException(sqlEx);
        }
    }

    @Override
    public void close(){
        super.close();
        //Dao Objects
        perfilDao = null;
        docenteDao = null;
        estudianteDao = null;
        cursoDao = null;
        asignaturaDao = null;
        eventoDao = null;
        //Runtime Dao
        perfilRuntimeDao = null;
        docenteRuntimeDao = null;
        estudianteRuntimeDao = null;
        cursoRuntimeDao = null;
        asignaturaRuntimeDao = null;
        eventoRuntimeDao = null;
    }

    //Getters de los modelos encargados de obtener los datos
    public Dao<Perfil, Integer> getPerfilDao() throws SQLException{
        if(perfilDao == null) perfilDao = getDao(Perfil.class);
        return perfilDao;
    }
    //Getters de los modelos encargados de manipulas los datos con excepciones de SQL
    public RuntimeExceptionDao<Perfil, Integer> getPerfilRuntimeDao() {
        if(perfilRuntimeDao == null) perfilRuntimeDao = getRuntimeExceptionDao(Perfil.class);
        return perfilRuntimeDao;
    }

    //Getters de los modelos encargados de obtener los datos
    public Dao<Docente, Integer> getDocenteDao() throws SQLException{
        if(docenteDao == null) docenteDao = getDao(Docente.class);
        return docenteDao;
    }
    //Getters de los modelos encargados de manipulas los datos con excepciones de SQL
    public RuntimeExceptionDao<Docente, Integer> getDocenteRuntimeDao() {
        if(docenteRuntimeDao == null) docenteRuntimeDao = getRuntimeExceptionDao(Docente.class);
        return docenteRuntimeDao;
    }

    //Getters del modelo estudiante asociado con el Dao de datos sin detalle de error
    public Dao<Estudiante, Integer> getEstudianteDao() throws SQLException{
        if(estudianteDao == null) estudianteDao = getDao(Estudiante.class);
        return estudianteDao;
    }
    //Getter del modelo estudiante asociado al Dao de operaciones con excepciones para errores
    public RuntimeExceptionDao<Estudiante, Integer> getEstudianteRuntimeDao(){
        if(estudianteRuntimeDao == null) estudianteRuntimeDao = getRuntimeExceptionDao(Estudiante.class);
        return estudianteRuntimeDao;
    }

    //Getters del modelo curso asociado con el Dao de datos sin detalle de error
    public Dao<Curso, Integer> getCursoDao() throws  SQLException{
        if (cursoDao == null) cursoDao = getDao(Curso.class);
        return cursoDao;
    }
    //Getter del modelo curso asociado al Dao de operaciones con excepciones para errores
    public RuntimeExceptionDao<Curso, Integer> getCursoRuntimeDao(){
        if(cursoRuntimeDao == null) cursoRuntimeDao = getRuntimeExceptionDao(Curso.class);
        return cursoRuntimeDao;
    }

    //Getters del modelo asignatura asociado con el Dao de datos sin detalle de error
    public Dao<Asignatura, Integer> getAsignaturaDao() throws  SQLException{
        if (asignaturaDao == null) asignaturaDao = getDao(Asignatura.class);
        return asignaturaDao;
    }
    //Getter del modelo asignatura asociado al Dao de operaciones con excepciones para errores
    public RuntimeExceptionDao<Asignatura, Integer> getAsignaturaRuntimeDao(){
        if(asignaturaRuntimeDao == null) asignaturaRuntimeDao = getRuntimeExceptionDao(Asignatura.class);
        return asignaturaRuntimeDao;
    }

    //Getters del modelo asignatura asociado con el Dao de datos sin detalle de error
    public Dao<Evento, Integer> getEventoDao() throws  SQLException{
        if (eventoDao == null) eventoDao = getDao(Evento.class);
        return eventoDao;
    }
    //Getter del modelo evento asociado al Dao de operaciones con excepciones para errores
    public RuntimeExceptionDao<Evento, Integer> getEventoRuntimeDao(){
        if(asignaturaRuntimeDao == null) eventoRuntimeDao = getRuntimeExceptionDao(Evento.class);
        return eventoRuntimeDao;
    }

}

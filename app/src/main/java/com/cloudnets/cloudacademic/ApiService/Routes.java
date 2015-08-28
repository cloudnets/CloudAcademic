package com.cloudnets.cloudacademic.ApiService;

import com.cloudnets.cloudacademic.Models.Perfil;
import com.google.gson.JsonObject;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;

/**
 * Creado por Deimer Villa on 04/08/2015.
 */
public interface Routes {

/*Seccion del servicio encargado de recibir datos de la API*/
/**************Seccion perfiles y usuarios************/
    @FormUrlEncoded
    @POST("/areas/api/perfil")
    void login(
            @Field("user")String  user,
            @Field("pass")String  pass,
            Callback<Perfil> cb
    );

/*****************Seccion docentes**************/
    @GET("/areas/api/docentes")
    void getDocentes(
            Callback<JsonObject> cb
    );

    @GET("/areas/api/asignaturas/docente")
    void getAsignaturasDocente(
            @Query("cod_docente")String  cod_docente,
            Callback<JsonObject> cb
    );

/*****************Seccion cursos**************/
    @GET("/areas/api/cursos")
    void getCursos(
            @Query("cod_docente")String  cod_docente,
            Callback<JsonObject> cb
    );

/**************Seccion estudiantes************/
    @GET("/areas/api/estudiantes")
    void getEstudiantes(
            @Query("cod_docente")String  cod_docente,
            Callback<JsonObject> cb
    );

    @GET("/areas/api/coordinador/estudiantes")
    void getCoordinadorEstudiantes(
            @Query("cod_coordinador")String  cod_docente,
            Callback<JsonObject> cb
    );

    @GET("/areas/api/asignaturas/estudiante")
    void getAsignaturasEstudiante(
            @Query("cod_estudiante")String cod_estudiante,
            Callback<JsonObject> cb
    );

}

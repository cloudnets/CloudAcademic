package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cloudnets.cloudacademic.Controllers.AsignaturaController;
import com.cloudnets.cloudacademic.Controllers.CursoController;
import com.cloudnets.cloudacademic.Controllers.DocenteController;
import com.cloudnets.cloudacademic.Controllers.EstudianteController;
import com.cloudnets.cloudacademic.Controllers.PerfilController;
import com.cloudnets.cloudacademic.Helpers.Funciones;
import com.cloudnets.cloudacademic.Implementacion.ImplementacionAsignatura;
import com.cloudnets.cloudacademic.Implementacion.ImplementacionCurso;
import com.cloudnets.cloudacademic.Implementacion.ImplementacionDocente;
import com.cloudnets.cloudacademic.Implementacion.ImplementacionEstudiante;
import com.cloudnets.cloudacademic.Models.Asignatura;
import com.cloudnets.cloudacademic.Models.Curso;
import com.cloudnets.cloudacademic.Models.Docente;
import com.cloudnets.cloudacademic.Models.Estudiante;
import com.cloudnets.cloudacademic.Models.Perfil;
import com.cloudnets.cloudacademic.R;
import com.cloudnets.cloudacademic.ApiService.Routes;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Creado por Deimer Villa on 29/06/2015.
 * ----------------------------------------------
 * Clase Login: Esta es la clase encargada de la
 * activity Login. En esta vista se encuentran las
 * funciones para validar las credenciales del usuario
 * al momento de entrar a la aplicacion y los datos de
 * la plataforma web.
 */
public class Login extends Activity {

    //Elementos de la vista
    public ImageView imgLogo;
    public LinearLayout contenedor_credenciales;
    public TextView txtUsuario;
    public TextView txtContrasena;
    public Button butValidar;
    private Funciones funciones;

    //Variables de valores para peticiones
    String user = "";
    String pass = "";
    String institucion = "";

    //Controlador del modelo entidad perfil
    private PerfilController perfilController;
    private DocenteController docenteController;
    private EstudianteController estudianteController;
    private CursoController cursoController;
    private AsignaturaController asignaturaController;
    //Contexto general de la aplicacion
    private Context contexto;

    //Objeto para validacion
    Perfil perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        inicializarContexto();
        inicializarElementosVista();
        contador();
        cambiarFondo();
    }

    public void inicializarContexto(){
        contexto = this;
        //Controladores
        perfilController = new PerfilController();
        docenteController = new DocenteController();
        estudianteController = new EstudianteController();
        cursoController = new CursoController();
        asignaturaController = new AsignaturaController();
        //Funciones
        funciones = new Funciones(contexto);
        user = "";
        pass = "";
    }

    public void inicializarElementosVista(){
        contenedor_credenciales = (LinearLayout) findViewById(R.id.contenedor_credenciales);
        txtUsuario = (TextView) findViewById(R.id.txtUsuario);
        txtContrasena = (TextView) findViewById(R.id.txtContrasena);
        butValidar = (Button) findViewById(R.id.butValidar);
        imgLogo = (ImageView) findViewById(R.id.logo_login);
        butValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean estadoInternet = funciones.hayConexion();
                if (estadoInternet) {
                    validarUsuario();
                } else {
                    funciones.alertasDialog(getString(R.string.error_1), getString(R.string.mensaje_alerta_1));
                }
            }
        });
    }

    public void contador(){
        int segundos = 2;
        int milisegundos = segundos*1000;
        new CountDownTimer(milisegundos,1000) {
            public void onTick(long millisUntilFinished){
            }
            public void onFinish(){
                moverLogo();
            }
        }.start();
    }

    public void cambiarFondo(){
        int segundos = 3;
        int milisegundos = segundos*1000;
        new CountDownTimer(milisegundos,1000) {
            public void onTick(long millisUntilFinished){
            }
            public void onFinish(){
                mostrarElementos();
            }
        }.start();
    }

    public void moverLogo(){
        Animation mover;
        mover = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.mover);
        mover.reset();
        mover.setFillAfter(true);
        imgLogo.startAnimation(mover);
    }

    public void mostrarElementos(){
        mostrarVistas(true);
        contenedor_credenciales.setVisibility(View.VISIBLE);
    }

    public void mostrarVistas(boolean state){
        if(state){
            Animation show;
            show = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.fade);
            show.reset();
            contenedor_credenciales.startAnimation(show);
        }
    }

    public void validarUsuario(){
        user = txtUsuario.getText().toString();
        pass = txtContrasena.getText().toString();
        if((user.equalsIgnoreCase(""))||(pass.equalsIgnoreCase(""))){
            funciones.alertasDialog(getString(R.string.error_2),
                    getString(R.string.mensaje_alerta_2));
        }else{
            obtenerPerfil();
        }
    }

    public void methodToHandleRetrofitError(RetrofitError error) {
        Log.e("Login(obtenerPerfil)", "Error: " + error.getMessage());
        Log.e("Login(obtenerPerfil)", "Response: " + error.getResponse());
    }

    public void obtenerPerfil(){
        funciones.alertasAsincronicas(getString(R.string.validar_1), getString(R.string.validar_2));
        final String url = getString(R.string.url_con);
        user = txtUsuario.getText().toString();
        pass = txtContrasena.getText().toString();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        Routes ruta = restAdapter.create(Routes.class);
        ruta.login(user, pass, new Callback<Perfil>() {
            @Override
            public void success(Perfil perfilResponse, Response response) {
                boolean success = perfilResponse.isSuccess();
                if (success) {
                    perfil = perfilResponse;
                    guardarPerfil(perfil, url);
                    funciones.cancelarDialog();
                    iniciarSesion();
                } else {
                    funciones.cancelarDialog();
                    funciones.alertasDialog("", getString(R.string.mensaje_alerta_3));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                try {
                    funciones.cancelarDialog();
                    Log.e("Login(obtenerPerfil)", "Error: " + error.getBody().toString());
                } catch (Exception ex) {
                    Log.e("Login(obtenerPerfil)", "Error ret: " + error + "; Error ex: " + ex.getMessage());
                    methodToHandleRetrofitError(error);
                }
            }
        });
    }

    public void obtenerDocentes(String url){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        Routes ruta = restAdapter.create(Routes.class);
        ruta.getDocentes(new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jSON, Response response) {
                String data = jSON.toString();
                try {
                    JSONObject json = new JSONObject(data);
                    boolean success = json.getBoolean("success");
                    if (success) {
                        JSONArray arrayData = json.getJSONArray("docentes");
                        guardarDocentes(arrayData);
                    }
                } catch (JSONException ex) {
                    Log.e("Login(obtenerDocentes)", "Json error: " + ex.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                try {
                    Log.e("Login(obtenerDocentes)", "Error: " + error.getBody().toString());
                } catch (Exception ex) {
                    Log.e("Login(obtenerDocentes)", "Error ex: " + ex.getMessage());
                    methodToHandleRetrofitError(error);
                }
            }
        });
    }

    public void obtenerEstudiantes(String cod_docente,String url){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        Routes ruta = restAdapter.create(Routes.class);
        ruta.getEstudiantes(cod_docente, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jSON, Response response) {
                String data = jSON.toString();
                System.out.println(data);
                try {
                    JSONObject json = new JSONObject(data);
                    boolean success = json.getBoolean("success");
                    if (success) {
                        String jsonEst = json.getString("estudiantes");
                        JSONArray arrayData = new JSONArray(jsonEst);
                        guardarEstudiantes(arrayData);
                    }
                } catch (JSONException ex) {
                    Log.e("Login(obtenerEstudiantes)", "Json error: " + ex.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                funciones.cancelarDialog();
                funciones.alertasDialog("Login(obtenerEstudiantes)", "Error: " + error.getBody().toString());
                Log.e("Login(obtenerEstudiantes)", "Error: " + error.getBody().toString());
            }
        });
    }

    public void obtenerCoordinadorEstudiantes(String cod_coordinador,String url){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        Routes ruta = restAdapter.create(Routes.class);
        ruta.getEstudiantes(cod_coordinador, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jSON, Response response) {
                String data = jSON.toString();
                System.out.println(data);
                try {
                    JSONObject json = new JSONObject(data);
                    boolean success = json.getBoolean("success");
                    if (success) {
                        String jsonEst = json.getString("estudiantes");
                        JSONArray arrayData = new JSONArray(jsonEst);
                        guardarEstudiantes(arrayData);
                    }
                } catch (JSONException ex) {
                    Log.e("Login(obtenerEstudiantes)", "Json error: " + ex.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                funciones.cancelarDialog();
                funciones.alertasDialog("Login(obtenerEstudiantes)", "Error: " + error.getBody().toString());
                Log.e("Login(obtenerEstudiantes)", "Error: " + error.getBody().toString());
            }
        });
    }

    public void obtenerCursos(String cod_docente,String url){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        Routes ruta = restAdapter.create(Routes.class);
        ruta.getCursos(cod_docente, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jSON, Response response) {
                String data = jSON.toString();
                try {
                    JSONObject json = new JSONObject(data);
                    boolean success = json.getBoolean("success");
                    if (success) {
                        String jsonCur = json.getString("cursos");
                        JSONArray arrayData = new JSONArray(jsonCur);
                        guardarCursos(arrayData);
                    }
                } catch (JSONException ex) {
                    Log.e("Login(obtenerCursos)", "Error: " + ex.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                funciones.cancelarDialog();
                funciones.alertasDialog("Login(obtenerCursos)", "Error: " + error.getBody().toString());
                Log.e("Login(obtenerCursos)", "Error: " + error.getBody().toString());
            }
        });
    }

    public void obtenerAsignaturasDocente(String url, String cod_docente){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        Routes ruta = restAdapter.create(Routes.class);
        ruta.getAsignaturasDocente(cod_docente, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jSON, Response response) {
                String data = jSON.toString();
                try {
                    JSONObject json = new JSONObject(data);
                    boolean success = json.getBoolean("success");
                    if (success) {
                        String jsonAsig = json.getString("asignaturas");
                        JSONArray arrayData = new JSONArray(jsonAsig);
                        guardarAsignaturasDocente(arrayData);
                    }
                } catch (JSONException ex) {
                    Log.e("Login(obtenerAsignaturasDocente)", "Error: " + ex.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                funciones.cancelarDialog();
                funciones.alertasDialog("Login(obtenerAsignaturasDocente)", "Error: " + error.getBody().toString());
                Log.e("Login(obtenerAsignaturasDocente)", "Error: " + error.getBody().toString());
            }
        });
    }

    public void obtenerAsignaturasEstudiante(String cod_estudiante, String url){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .build();
        Routes ruta = restAdapter.create(Routes.class);
        ruta.getAsignaturasEstudiante(cod_estudiante, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jSON, Response response) {
                String data = jSON.toString();
                try {
                    JSONObject json = new JSONObject(data);
                    boolean success = json.getBoolean("success");
                    if (success) {
                        String jsonAsig = json.getString("asignaturas");
                        JSONArray arrayData = new JSONArray(jsonAsig);
                        guardarAsignaturasEstudiante(arrayData);
                    }
                } catch (JSONException ex) {
                    Log.e("Login(obtenerAsignaturasEstudiante)", "Error: " + ex.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                funciones.cancelarDialog();
                funciones.alertasDialog("Login(obtenerAsignaturasDocente)", "Error: " + error.getBody().toString());
                Log.e("Login(obtenerAsignaturasDocente)", "Error: " + error.getBody().toString());
            }
        });
    }

    /***************Procesos de almacenado***************/
    public boolean guardarPerfil(Perfil perfil, String url){
        boolean res = false;
        try {
            boolean success = perfil.isSuccess();
            if(success){
                res = perfilController.crear(perfil, contexto);
                cargarConfiguracion(perfil,url);
            }else{
                funciones.alertasDialog(getString(R.string.error_3),
                        getString(R.string.mensaje_alerta_3));
            }
        } catch (Exception ex) {
            Log.e("Login(guardarPerfil)", "Error: " + ex.getMessage());
        }
        return res;
    }

    public boolean guardarDocentes(JSONArray listaDocentes){
        boolean res = true;
        ImplementacionDocente iD = new ImplementacionDocente();
        try {
            for (int i = 0; i < listaDocentes.length(); i++) {
                JSONObject jsonDocente = listaDocentes.getJSONObject(i);
                Docente docente = new Gson().fromJson(jsonDocente.toString(), Docente.class);
                res = docenteController.crear(docente, contexto);
                System.out.println(iD.showDetails(docente));
                if(!res){
                    res = false;
                    break;
                }
            }
        }catch (Exception ex){
            res = false;
            Log.e("Login(guardarDocentes)", "Error: " + ex.getMessage());
        }
        return res;
    }

    public boolean guardarEstudiantes(JSONArray listaEstudiantes){
        boolean res = true;
        ImplementacionEstudiante iE = new ImplementacionEstudiante();
        try {
            for (int i = 0; i < listaEstudiantes.length(); i++) {
                JSONObject jsonEstudiante = listaEstudiantes.getJSONObject(i);
                System.out.println(jsonEstudiante.toString());
                Estudiante estudiante = new Gson().fromJson(jsonEstudiante.toString(), Estudiante.class);
                res = estudianteController.crear(estudiante, contexto);
                System.out.println(iE.showDetails(estudiante));
                if(!res){
                    res = false;
                    break;
                }
            }
        }catch (Exception ex){
            res = false;
            Log.e("Login(guardarEstudiantes)", "Error: " + ex.getMessage());
        }
        return res;
    }

    public boolean guardarCursos(JSONArray listaCursos){
        boolean res = true;
        ImplementacionCurso iC = new ImplementacionCurso();
        try {
            for (int i = 0; i < listaCursos.length(); i++) {
                JSONObject jsonCurso = listaCursos.getJSONObject(i);
                Curso curso = new Gson().fromJson(jsonCurso.toString(), Curso.class);
                res = cursoController.crear(curso, contexto);
                System.out.println(iC.showDetails(curso));
                if(!res){
                    res = false;
                    break;
                }
            }
        }catch (Exception ex){
            res = false;
            Log.e("Login(guardarCursos)", "Error: " + ex.getMessage());
        }
        return res;
    }

    public boolean guardarAsignaturasDocente(JSONArray listaAsignaturas){
        boolean res = true;
        ImplementacionAsignatura iA = new ImplementacionAsignatura();
        try {
            for (int i = 0; i < listaAsignaturas.length(); i++) {
                JSONObject jsonAsignatura = listaAsignaturas.getJSONObject(i);
                Asignatura asignatura = new Gson().fromJson(jsonAsignatura.toString(), Asignatura.class);
                res = asignaturaController.crear(asignatura, contexto);
                System.out.println(iA.showDetails(asignatura));
                if(!res){
                    res = false;
                    break;
                }
            }
        }catch (Exception ex){
            res = false;
            Log.e("Login(guardarAsignaturasDocente)", "Error: " + ex.getMessage());
        }
        return res;
    }

    public boolean guardarAsignaturasEstudiante(JSONArray listaAsignaturas){
        boolean res = true;
        ImplementacionAsignatura iA = new ImplementacionAsignatura();
        try {
            for (int i = 0; i < listaAsignaturas.length(); i++) {
                JSONObject jsonAsignatura = listaAsignaturas.getJSONObject(i);
                Asignatura asignatura = new Gson().fromJson(jsonAsignatura.toString(), Asignatura.class);
                res = asignaturaController.crear(asignatura, contexto);
                System.out.println(iA.showDetails(asignatura));
                if(!res){
                    res = false;
                    break;
                }
            }
        }catch (Exception ex){
            res = false;
            Log.e("Login(guardarAsignaturasEstudiante)", "Error: " + ex.getMessage());
        }
        return res;
    }

    public void cargarConfiguracion(Perfil perfil, String url){
        String tipoUsuario = perfil.getTipoUsuario();
        String codigo = perfil.getIdentificacion();
        switch (tipoUsuario){
            case "Administrador":
                obtenerDocentes(url);
                break;
            case "Coordinador":
                obtenerDocentes(url);
                obtenerCoordinadorEstudiantes(codigo, url);
                obtenerCursos(codigo, url);
                break;
            case "Docente":
                obtenerEstudiantes(codigo,url);
                obtenerCursos(codigo, url);
                obtenerAsignaturasDocente(codigo,url);
                break;
            case "Estudiante":
                obtenerAsignaturasEstudiante(codigo,url);
                break;
        }
    }

    public void iniciarSesion(){
        Intent login = new Intent(Login.this,Principal.class);
        login.putExtra("institucion", institucion);
        startActivity(login);
    }

}

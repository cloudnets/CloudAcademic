package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cloudnets.cloudacademic.Controllers.*;
import com.cloudnets.cloudacademic.Helpers.Funciones;
import com.cloudnets.cloudacademic.Implementacion.*;
import com.cloudnets.cloudacademic.Models.*;
import com.cloudnets.cloudacademic.R;
import com.google.gson.Gson;
import com.loopj.android.http.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.*;
import java.util.ArrayList;
import java.util.List;

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
    Perfil perfil;

    //Controlador del modelo entidad perfil
    private PerfilController perfilController;
    private DocenteController docenteController;
    private EstudianteController estudianteController;
    private CursoController cursoController;
    private AsignaturaController asignaturaController;
    //Contexto general de la aplicacion
    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        inicializarContexto();
        inicializarElementosVista();
        contador();
        iniciarElementos();
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

    public void iniciarElementos(){
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
            //obtenerPerfil();
            iniciarSesion2();
        }
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
        //ImplementacionEstudiante iE = new ImplementacionEstudiante();
        try {
            for (int i = 0; i < listaEstudiantes.length(); i++) {
                JSONObject jsonEstudiante = listaEstudiantes.getJSONObject(i);
                System.out.println(jsonEstudiante.toString());
                Estudiante estudiante = new Gson().fromJson(jsonEstudiante.toString(), Estudiante.class);
                res = estudianteController.crear(estudiante, contexto);
                //System.out.println(iE.showDetails(estudiante));
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
        //ImplementacionCurso iC = new ImplementacionCurso();
        try {
            for (int i = 0; i < listaCursos.length(); i++) {
                JSONObject jsonCurso = listaCursos.getJSONObject(i);
                Curso curso = new Gson().fromJson(jsonCurso.toString(), Curso.class);
                res = cursoController.crear(curso, contexto);
                //System.out.println(iC.showDetails(curso));
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
        //ImplementacionAsignatura iA = new ImplementacionAsignatura();
        try {
            for (int i = 0; i < listaAsignaturas.length(); i++) {
                JSONObject jsonAsignatura = listaAsignaturas.getJSONObject(i);
                Asignatura asignatura = new Gson().fromJson(jsonAsignatura.toString(), Asignatura.class);
                res = asignaturaController.crear(asignatura, contexto);
                //System.out.println(iA.showDetails(asignatura));
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

//////////////////////////////////////////////////////////////////////////////////////////////////
    public Boolean login(String user, String pass, String ipURL){
        boolean res = false;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost postLogin = new HttpPost(ipURL+"/areas/api/perfil");
        try{
            List<NameValuePair> parametros = new ArrayList<>();
            parametros.add(new BasicNameValuePair("user", user));
            parametros.add(new BasicNameValuePair("pass", pass));
            postLogin.setEntity(new UrlEncodedFormEntity(parametros));
            HttpResponse resp = httpClient.execute(postLogin);
            String response = EntityUtils.toString(resp.getEntity());
            try {
                JSONObject json = new JSONObject(response);
                boolean success = json.getBoolean("success");
                if(success){
                    perfil = new Gson().fromJson(json.toString(), Perfil.class);
                    res = perfilController.crear(perfil, contexto);
                    if(res){
                        cargarConfiguracion(perfil,ipURL);
                    }
                }
            } catch (JSONException e) {
                Log.i("ImplementacionLogin(Login)", "Error JSONException: " + e.toString());
            }
        }catch(Exception e){
            Log.i("ImplementacionLogin(Login)","Error Exception: "+e.toString());
        }
        return res;
    }

    public Boolean obtenerDocentes(String ipURL){
        boolean res = false;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getDocentes = new HttpGet(ipURL+"/areas/api/docentes");
        getDocentes.setHeader("content-type", "application/json");
        try{
            HttpResponse resp = httpClient.execute(getDocentes);
            String response = EntityUtils.toString(resp.getEntity());
            try {
                JSONObject json = new JSONObject(response);
                boolean success = json.getBoolean("success");
                if(success){
                    JSONArray arrayData = json.getJSONArray("docentes");
                    res = guardarDocentes(arrayData);
                }
            } catch (JSONException e) {
                Log.i("ImplementacionLogin(Login)", "Error JSONException: " + e.toString());
            }
        }catch(Exception e){
            Log.i("ImplementacionLogin(Login)","Error Exception: "+e.toString());
        }
        return res;
    }

    public Boolean obtenerEstudiantes(String cod_docente, String ipURL){
        boolean res = false;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getEstudiantes = new HttpGet(ipURL+"/areas/api/estudiantes?cod_docente="+cod_docente);
        getEstudiantes.setHeader("content-type", "application/json");
        try{
            HttpResponse resp = httpClient.execute(getEstudiantes);
            String response = EntityUtils.toString(resp.getEntity());
            try {
                JSONObject json = new JSONObject(response);
                boolean success = json.getBoolean("success");
                if(success){
                    JSONArray arrayData = json.getJSONArray("estudiantes");
                    res = guardarEstudiantes(arrayData);
                }
            } catch (JSONException e) {
                Log.i("ImplementacionLogin(Login)", "Error JSONException: " + e.toString());
            }
        }catch(Exception e){
            Log.i("ImplementacionLogin(Login)","Error Exception: "+e.toString());
        }
        return res;
    }

    public Boolean obtenerCoordinadorEstudiantes(String cod_coordinador, String ipURL){
        boolean res = false;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getEstudiantes = new HttpGet(ipURL+"/areas/api/coordinador/estudiantes?cod_coordinador="+cod_coordinador);
        getEstudiantes.setHeader("content-type", "application/json");
        try{
            HttpResponse resp = httpClient.execute(getEstudiantes);
            String response = EntityUtils.toString(resp.getEntity());
            try {
                JSONObject json = new JSONObject(response);
                boolean success = json.getBoolean("success");
                if(success){
                    JSONArray arrayData = json.getJSONArray("estudiantes");
                    res = guardarEstudiantes(arrayData);
                }
            } catch (JSONException e) {
                Log.i("Login(obtenerCoordinadorEstudiantes)", "Error JSONException: " + e.toString());
            }
        }catch(Exception e){
            Log.i("Login(obtenerCoordinadorEstudiantes)","Error Exception: "+e.toString());
        }
        return res;
    }

    public Boolean obtenerCursos(String cod_docente, String ipURL){
        boolean res = false;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getCursos = new HttpGet(ipURL+"/areas/api/cursos?cod_docente="+cod_docente);
        getCursos.setHeader("content-type", "application/json");
        try{
            HttpResponse resp = httpClient.execute(getCursos);
            String response = EntityUtils.toString(resp.getEntity());
            try {
                JSONObject json = new JSONObject(response);
                boolean success = json.getBoolean("success");
                if(success){
                    JSONArray arrayData = json.getJSONArray("cursos");
                    res = guardarCursos(arrayData);
                }
            } catch (JSONException e) {
                Log.i("Login(obtenerCursos)", "Error JSONException: " + e.toString());
            }
        }catch(Exception e){
            Log.i("Login(obtenerCursos)","Error Exception: "+e.toString());
        }
        return res;
    }

    public Boolean obtenerAsignaturasDocente(String cod_docente, String ipURL){
        boolean res = false;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getCursos = new HttpGet(ipURL+"/areas/api/asignaturas/docente?cod_docente="+cod_docente);
        getCursos.setHeader("content-type", "application/json");
        try{
            HttpResponse resp = httpClient.execute(getCursos);
            String response = EntityUtils.toString(resp.getEntity());
            try {
                JSONObject json = new JSONObject(response);
                boolean success = json.getBoolean("success");
                if(success){
                    JSONArray arrayData = json.getJSONArray("asignaturas");
                    res = guardarAsignaturasDocente(arrayData);
                }
            } catch (JSONException e) {
                Log.i("Login(obtenerAsignaturasDocente)", "Error JSONException: " + e.toString());
            }
        }catch(Exception e){
            Log.i("Login(obtenerAsignaturasDocente)","Error Exception: "+e.toString());
        }
        return res;
    }

    public Boolean obtenerAsignaturasEstudiante(String cod_estudiante, String ipURL){
        boolean res = false;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getCursos = new HttpGet(ipURL+"/areas/api/asignaturas/docente?cod_estudiante="+cod_estudiante);
        getCursos.setHeader("content-type", "application/json");
        try{
            HttpResponse resp = httpClient.execute(getCursos);
            String response = EntityUtils.toString(resp.getEntity());
            try {
                JSONObject json = new JSONObject(response);
                boolean success = json.getBoolean("success");
                if(success){
                    JSONArray arrayData = json.getJSONArray("asignaturas");
                    res = guardarAsignaturasEstudiante(arrayData);
                }
            } catch (JSONException e) {
                Log.i("Login(obtenerAsignaturasEstudiante)", "Error JSONException: " + e.toString());
            }

        }catch(Exception e){
            Log.i("Login(obtenerAsignaturasEstudiante)","Error Exception: "+e.toString());
        }

        return res;
    }

    public void iniciarSesion2(){
        user = txtUsuario.getText().toString();
        pass = txtContrasena.getText().toString();
        login logout = new login();
        logout.execute();
    }
    private class login extends AsyncTask<String, Integer, Boolean> {
        protected void onPreExecute(){
            funciones.alertasAsincronicas(getString(R.string.validar_1), getString(R.string.validar_2));
        }
        protected Boolean doInBackground(String... params) {
            boolean res;
            final String url = getString(R.string.url_con);
            res = login(user, pass, url);
            return res;
        }
        public void onPostExecute(Boolean resul){
            funciones.cancelarDialog();
            if(resul){
                funciones.cancelarDialog();

                iniciarSesion();
            }else{
                funciones.alertasDialog("", getString(R.string.mensaje_alerta_3));
            }
        }
    }

    public void iniciarSesion(){
        Intent login = new Intent(Login.this,Principal.class);
        login.putExtra("id", perfil.getId());
        startActivity(login);
    }

}

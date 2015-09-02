package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cloudnets.cloudacademic.Controllers.PerfilController;
import com.cloudnets.cloudacademic.Helpers.Funciones;
import com.cloudnets.cloudacademic.Implementacion.ImplementacionPerfil;
import com.cloudnets.cloudacademic.Models.Perfil;
import com.cloudnets.cloudacademic.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created por Deimer on 29/06/2015.
 * ----------------------------------------------
 * Clase Principal: Esta es la clase encargada de la
 * activity principal. En esta vista se encuentran las
 * funciones para seleccionar una opcion de la funcion
 * a realizar con la app.
 */
public class Principal extends Activity {

    //Elementos de la vista
    private TextView lblFullName;
    private TextView lblTipoUsuario;
    ImageButton iconMenuPrincipal;
    ImageButton iconMenuPerfil;
    LinearLayout contenedorCardMenu1;
    //Controlador de la clase perfil
    private PerfilController perfilController;
    private ImplementacionPerfil iP;
    private Funciones funciones;
    //Contexto general de la aplicacion
    private Context contexto;
    //ID usuario actvio
    int id;
    //Usuario coordinador
    String usuarioCoordinador;
    //Control de GSM
    private GoogleCloudMessaging gcm;
    private String regid;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final String PROPERTY_EXPIRATION_TIME = "onServerExpirationTimeMs";
    private static final String PROPERTY_USER = "user";
    public static final long EXPIRATION_TIME_MS = 1000 * 3600 * 24 * 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_principal);
        idUser();
        inicializarContexto();
        inicializarElementosPrincipal();
        registrarApiKey(usuarioCoordinador);
    }

    public void idUser(){
        PerfilController perfilController = new PerfilController();
        Perfil perfil = perfilController.buscarPerfil(contexto);
        usuarioCoordinador = perfil.getUsuario();
        id = perfil.getId();
    }

    public void inicializarContexto(){
        contexto = this;
        iP = new ImplementacionPerfil();
        perfilController = new PerfilController();
        funciones = new Funciones(contexto);
    }

    public void inicializarElementosPrincipal(){
        //Etiquetas
        lblFullName = (TextView) findViewById(R.id.lblNombres);
        lblFullName.setTextColor(Color.WHITE);
        lblFullName.setTextSize(13);
        lblTipoUsuario = (TextView) findViewById(R.id.lblTipoUsuario);
        lblTipoUsuario.setTextColor(Color.WHITE);
        lblTipoUsuario.setTextSize(13);
        //Layouts
        contenedorCardMenu1 = (LinearLayout) findViewById(R.id.contenedor_card_menu_1);
        contenedorCardMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaEstudiantes();
            }
        });
        //Iconos
        iconMenuPrincipal = (ImageButton) findViewById(R.id.iconMenuPrincipal);
        iconMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funciones.alertasDialog("¿Quienes somos?",getString(R.string.about));
            }
        });
        iconMenuPerfil = (ImageButton) findViewById(R.id.iconoPerfil);
        iconMenuPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Perfil perfil = perfilController.detalle(id,contexto);
                funciones.alertasDialog("Info perfil", iP.mostrarDetalles(perfil));
            }
        });
        cargarDatoPerfil();
    }

    public void cargarDatoPerfil(){
        try {
            Perfil perfil = perfilController.detalle(id,this.getApplicationContext());
            String nombres = perfil.getNombres();
            String tipoUsuario = perfil.getTipoUsuario();
            lblFullName.setText(nombres);
            lblTipoUsuario.setText(tipoUsuario);
        }catch (Exception ex){
            Log.e("Principal(cargarDatosPerfil)","Error: "+ex.getMessage());
        }
    }

    /************************Funciones de los iconos************************/
    public void mensajeCerrar(){
        AlertDialog.Builder dialogoCerrar = new AlertDialog.Builder(this);
        dialogoCerrar.setTitle(getString(R.string.confirmacion_1));
        dialogoCerrar.setMessage(getString(R.string.mensaje_confirmacion_1));
        dialogoCerrar.setCancelable(false);
        dialogoCerrar.setPositiveButton(getString(R.string.si), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                try{
                    boolean res = perfilController.cerrarSesion(id,contexto);
                    if(res){
                        cerrarSesion();
                    }
                }catch (Throwable e){
                    e.printStackTrace();
                }
            }
        });
        dialogoCerrar.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialogoCerrar.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            mensajeCerrar();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /************************Gestion y ciclos de las activities************************/
    public void cerrarSesion(){
        Intent login = new Intent(Principal.this,Login.class);
        startActivity(login);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    public void listaEstudiantes(){
        Intent lista = new Intent(Principal.this,ListaEstudiantes.class);
        lista.putExtra("push",true);
        startActivity(lista);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();
    }

    //*****************************************Seccion GCM*******************************************//
    //Funcion que permite validar si el equipo cuenta con Google play services
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode,this,PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }else{
                Log.i("Principal(checkPlayServices)", "Dispositivo no soportado.");
                finish();
            }
            return false;
        }
        return true;
    }

    //Funcion que permite registrar el usuario y el equipo para las notificaciones
    public void registrarApiKey(String user){
        //if(checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(contexto);
            regid = getRegistration(contexto, user);
            if(regid.equalsIgnoreCase("")){
                TareaRegistroGCM tarea = new TareaRegistroGCM();
                tarea.execute(user);
            }else{
                Log.i("Principal(registrarApiKey)", "No se ha encontrado Google Play Services.");
            }
        //}
    }

    //Funcion que obtiene el id del registro de las notificaciones
    private String getRegistration(Context context, String user){
        String registrationId;
        SharedPreferences preferences = getSharedPreferences(Login.class.getSimpleName(),Context.MODE_PRIVATE);
        registrationId = preferences.getString(PROPERTY_REG_ID,"");
        if(registrationId.length() == 0){
            Log.i("Principal(getRegistration)","Registro no encontrado");
            return "";
        }
        String registeredUser = preferences.getString(PROPERTY_USER,"user");
        int registeredVersion = preferences.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        long expirationTime = preferences.getLong(PROPERTY_EXPIRATION_TIME, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String expirationDate = sdf.format(new Date(expirationTime));
        Log.i("Login(getRegistration)", "Registro GCM encontrado (usuario=" + registeredUser +
                ", version=" + registeredVersion +
                ", expira=" + expirationDate + ")");
        int currentVersion = getAppVersion(context);
        if(registeredVersion != currentVersion){
            Log.i("Principal(getRegistration)","Nueva version de la app");
            return "";
        }else if(System.currentTimeMillis() > expirationTime){
            Log.i("Principal(getRegistration)","Registro GCM expirado");
            return "";
        }else if(!user.equalsIgnoreCase(registeredUser)){
            Log.i("Principal(getRegistration)","Nuevo nombre de usuario.");
            return "";
        }
        return registrationId;
    }

    //Funcion que permite obtener la version del sistema operativo del equipo
    private static int getAppVersion(Context context) {
        try{
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        }catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Error al obtener versión: " + e);
        }
    }

    //Funcion que envia a los servidores de google el registration_id del equipo y el usuario
    private class TareaRegistroGCM extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {
            String msg = "";
            try {
                if(gcm == null){
                    gcm = GoogleCloudMessaging.getInstance(contexto);
                }
                String SENDER_ID = "37900791807";
                regid = gcm.register(SENDER_ID);
                Log.i("Principal(TareaRegistroGCM)", "Registrado en GCM: registration_id=" + regid);
                String ipURL = getString(R.string.url_con);
                boolean registrado = registroServidor(params[0], regid, ipURL);
                if(registrado){
                    setRegistrationId(contexto, params[0], regid);
                }
            }catch (IOException ex){
                Log.e("Principal(TareaRegistroGCM)","Error registro en GCM:" + ex.getMessage());
            }
            return msg;
        }
    }

    //Funcion que registra en el servidor local del CloudAcademic los datos del usuario
    private boolean registroServidor(String usuario, String regId, String ipURL) {
        boolean reg = false;
        String id_categoria = "1";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost postRegistro = new HttpPost(ipURL+"/areas/api/registro/gcm");
        try{
            List<NameValuePair> parametros = new ArrayList<>();
            parametros.add(new BasicNameValuePair("usuario", usuario));
            parametros.add(new BasicNameValuePair("codigoC2DM", regId));
            parametros.add(new BasicNameValuePair("id_categoria", id_categoria));
            postRegistro.setEntity(new UrlEncodedFormEntity(parametros));
            HttpResponse resp = httpClient.execute(postRegistro);
            String response = EntityUtils.toString(resp.getEntity());
            System.out.println(response);
            try {
                JSONObject json = new JSONObject(response);
                boolean success = json.getBoolean("success");
                if(success){
                    Log.i("Principal(registroServidor)", "Registrado en el servidor de CloudNets.");
                    reg = true;
                }
            }catch (JSONException ex){
                Log.i("Principal(registroServidor)", "Errer JSON: "+ex.getCause()+" "+ex.getMessage());
            }
        }catch(Exception e){
            Log.i("Principal(registroServidor)","Error Exception: "+e.getCause() + " || " + e.getMessage());
        }
        return reg;
    }

    /*Funcion que permite registrar en las preferencias del sistema nuestro sistema de mensajes
    * Tambien nos permitira activar el servicio de notificaciones en el GCM (Google Cloud Message)
    * */
    private void setRegistrationId(Context context, String user, String regId) {
        SharedPreferences prefs = getSharedPreferences(Principal.class.getSimpleName(), Context.MODE_PRIVATE);
        int appVersion = getAppVersion(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_USER, user);
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.putLong(PROPERTY_EXPIRATION_TIME, System.currentTimeMillis() + EXPIRATION_TIME_MS);
        editor.apply();
    }

}

package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
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
import com.cloudnets.cloudacademic.Controllers.UsuarioController;
import com.cloudnets.cloudacademic.Helpers.Funciones;
import com.cloudnets.cloudacademic.Implementacion.ImplementacionUsuario;
import com.cloudnets.cloudacademic.Models.Proceso;
import com.cloudnets.cloudacademic.Models.Usuario;
import com.cloudnets.cloudacademic.R;
import com.cloudnets.cloudacademic.Requests.RequestLogin;

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

    public ImageView imgLogo;
    public LinearLayout contenedor_credenciales;
    public TextView txtUsuario;
    public TextView txtContrasena;
    public Button butValidar;
    private RequestLogin requestLogin;
    private Funciones funciones;
    String user = "";
    String pass = "";
    //Variable privada para el acceso al controlador del usuario
    private UsuarioController usuarioController;
    //Clase implementadora para las funciones de transcicion
    private ImplementacionUsuario implUsuario = new ImplementacionUsuario();

    private Context context = this;

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
        requestLogin = new RequestLogin(context);
        funciones = new Funciones(context);
        user = "";
        pass = "";
        usuarioController = new UsuarioController();
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
                logoMove();
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
                //contenedor_login.setBackgroundResource(R.mipmap.login);
                //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                show();
            }
        }.start();
    }

    public void logoMove(){
        Animation mover;
        mover = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.mover);
        mover.reset();
        mover.setFillAfter(true);
        imgLogo.startAnimation(mover);
    }

    public void show(){
        showViews(true);
        contenedor_credenciales.setVisibility(View.VISIBLE);
    }

    public void showViews(boolean state){
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
        if((user.equalsIgnoreCase(""))&&(pass.equalsIgnoreCase(""))){
            funciones.alertasDialog(getString(R.string.error_2),
                    getString(R.string.mensaje_alerta_2));
        }else{
            peticionLogin();
        }
    }

    public void peticionLogin(){
        loginAsincronico login = new loginAsincronico();
        login.execute();
    }

    private class loginAsincronico extends AsyncTask<String, Integer, Boolean>{
        String ipURL = "";
        Proceso proceso = new Proceso();
        protected void onPreExecute(){
            //Direccion url
            ipURL = getString(R.string.url_con);
            funciones.alertasAsincronicas(getString(R.string.validar_1), getString(R.string.validar_2));
        }
        protected Boolean doInBackground(String... par) {
            proceso = requestLogin.Login(user, pass, ipURL);
            return proceso.isResultado();
        }
        public void onPostExecute(Boolean result){
            funciones.cancelarDialog();
            if(result){
                mostrarInfo();
            }else{
                txtContrasena.setText("");
                funciones.alertasDialog(proceso.getTitulo(),proceso.getMensaje());
            }
        }
    }

    public void mostrarInfo(){
        try {
            Usuario usuario = usuarioController.detalle(1, this.getApplicationContext());
            funciones.alertasDialog("informacion del usuario", implUsuario.mostrarDetalles(usuario));
            //Log.i("Usuario", implUsuario.mostrarDetalles(usuario));
        }catch (Exception ex){
            Log.e("Error al mostrar","Error: "+ex.getMessage().toString());
        }
    }

}

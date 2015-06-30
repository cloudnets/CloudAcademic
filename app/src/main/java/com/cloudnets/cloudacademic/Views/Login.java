package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cloudnets.cloudacademic.R;

/**
 * Created by Deimer on 29/06/2015.
 * ----------------------------------------------
 * Clase Login: Esta es la clase encargada de la
 * activity Login. En esta vista se encuentran las
 * funciones para validar las credenciales del usuario
 * al momento de entrar a la aplicacion y los datos de
 * la plataforma web.
 */
public class Login extends Activity {

    private ImageView imgLogo;
    private RelativeLayout contenedor_login;
    private LinearLayout contenedor_credenciales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        inicializarElementosVista();
        contador();
    }

    public void inicializarElementosVista(){
        contenedor_login = (RelativeLayout) findViewById(R.id.contenedor_login);
        contenedor_credenciales = (LinearLayout) findViewById(R.id.contenedor_credenciales);
        imgLogo = (ImageView) findViewById(R.id.logo_login);
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
                contenedor_login.setBackgroundResource(R.mipmap.login);
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

    public void showViews(){
        Animation show;
        show = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.fade);
        show.reset();
        show.setFillAfter(true);
        contenedor_credenciales.startAnimation(show);
    }

}

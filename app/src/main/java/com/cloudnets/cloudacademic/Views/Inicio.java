package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import com.cloudnets.cloudacademic.R;

/**
 * Created by Deimer on 29/06/2015.
 * ----------------------------------------------
 * Clase Inicio: Esta es la clase encargada de la
 * activity Inicio. En esta vista se encuentran las
 * funciones para iniciar la aplicacion y los servicios
 * que esta utiliza para su correcto arranque.
 */
public class Inicio extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inicio);
        inicializarElementosVista();
        inicializarProgreso();
    }

    /**Funciones para inicializar los elementos en la vista
     *****/
    public void inicializarElementosVista(){
        ProgressBar pbProgreso = (ProgressBar) findViewById(R.id.pbProgreso);
    }

    /**Funcion encargada del manejo de los recursos en la vista
     *****/
    public void inicializarProgreso(){
        Thread timer = new Thread() {
            // El nuevo Thread exige el metodo run
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent login = new Intent(Inicio.this,Login.class);
                    startActivity(login);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

}

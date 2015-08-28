package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import com.cloudnets.cloudacademic.Controllers.*;
import com.cloudnets.cloudacademic.Implementacion.ImplementacionPerfil;
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

    //Elemento de carga dentro de la activida
    ProgressBar pbProgreso;
    //Clase controladora de consultas del objeto perfil
    private PerfilController perfilController = new PerfilController();
    private DocenteController docenteController = new DocenteController();
    private EstudianteController estudianteController = new EstudianteController();
    private ImplementacionPerfil implementacionPerfil = new ImplementacionPerfil();
    //Contexto general de la aplicacion
    private Context contexto = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inicio);
        inicializarElementosVista();
        inicializarProgreso();
    }

    /**Funciones para inicializar los elementos en la vista*****/
    public void inicializarElementosVista(){
        pbProgreso = (ProgressBar) findViewById(R.id.pbProgreso);
    }

    public void inicializarProgreso(){
        Thread timer = new Thread() {
            // El nuevo Thread exige el metodo run
            public void run() {
                try {
                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    haySesionActiva();
                }
            }
        };
        timer.start();
    }

    public void haySesionActiva(){
        boolean sesionActiva = perfilController.sesionActiva(contexto);
        if(sesionActiva){
            Intent principal = new Intent(Inicio.this,Principal.class);
            startActivity(principal);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }else{
            Intent login = new Intent(Inicio.this,Login.class);
            startActivity(login);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

}

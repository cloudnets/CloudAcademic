package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
    private TextView lblNombres;
    ImageButton iconMenuPrincipal;
    ImageButton iconMenuPerfil;
    LinearLayout contenedorCardMenu1;
    //Controlador de la clase perfil
    private PerfilController perfilController;
    private ImplementacionPerfil iP;
    private Funciones funciones;
    //Contexto general de la aplicacion
    private Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_principal);
        inicializarContexto();
        inicializarElementosPrincipal();
    }

    public void inicializarContexto(){
        contexto = this;
        iP = new ImplementacionPerfil();
        perfilController = new PerfilController();
        funciones = new Funciones(contexto);
    }

    public void inicializarElementosPrincipal(){
        //Etiquetas
        lblNombres = (TextView) findViewById(R.id.lblNombres);
        lblNombres.setTextColor(Color.WHITE);
        lblNombres.setTextSize(16);
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
                Perfil perfil = perfilController.detalle(1, contexto);
                funciones.alertasDialog("Info perfil", iP.mostrarDetalles(perfil));
            }
        });
        cargarDatoPerfil();
    }

    public void cargarDatoPerfil(){
        try {
            Perfil perfil = perfilController.detalle(1, this.getApplicationContext());
            String nombres = perfil.getNombres();
            String[] cadena = nombres.split(" ");
            String p_nombre = cadena[0];
            lblNombres.setText(p_nombre);
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
                    boolean res = perfilController.cerrarSesion(contexto);
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
        Intent login = new Intent(Principal.this,ListaEstudiantes.class);
        startActivity(login);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();
    }

}

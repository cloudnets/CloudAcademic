package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloudnets.cloudacademic.Controllers.EstudianteController;
import com.cloudnets.cloudacademic.Controllers.PerfilController;
import com.cloudnets.cloudacademic.Helpers.Funciones;
import com.cloudnets.cloudacademic.Implementacion.ImplementacionPerfil;
import com.cloudnets.cloudacademic.Models.Estudiante;
import com.cloudnets.cloudacademic.Models.Perfil;
import com.cloudnets.cloudacademic.R;

import java.util.ArrayList;
import java.util.List;

public class ListaEstudiantes extends Activity {

    //Controladores
    private EstudianteController estudianteController = new EstudianteController();
    //Elementos de la vista
    private TextView lblFullName;
    private TextView lblTipoUsuario;
    ImageButton iconMenuPrincipal;
    ImageButton iconMenuPerfil;
    RecyclerView recycler;
    LinearLayoutManager linManager;
    //Controlador de la clase perfil
    private PerfilController perfilController;
    private ImplementacionPerfil iP;
    private Funciones funciones;
    //Contexto general de la aplicacion
    private Context contexto;
    //ID usuario actvio
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lista_estudiantes);
        idUser();
        inicializarContexto();
        inicializarElementosEstudiantes();
        mostrarEstudiantes();
    }

    public void mostrarEstudiantes(){
        List<Estudiante> lista = estudianteController.obtenerEstudiantes(contexto);
        if(!lista.isEmpty()){
            for (int i = 0; i < lista.size(); i++) {
                Estudiante est = lista.get(i);
                System.out.println(est.toString());
            }
        }
        System.out.println("Cantidad est: " + lista.size());
    }

    public void idUser(){
        Bundle b = getIntent().getExtras();
        id = b.getInt("id");
    }

    public void inicializarContexto(){
        contexto = this;
        iP = new ImplementacionPerfil();
        perfilController = new PerfilController();
        funciones = new Funciones(contexto);
    }

    public void inicializarElementosEstudiantes(){
        //Etiquetas
        lblFullName = (TextView) findViewById(R.id.lblNombresLista);
        lblFullName.setTextColor(Color.WHITE);
        lblFullName.setTextSize(13);
        lblTipoUsuario = (TextView) findViewById(R.id.lblTipoUsuarioLista);
        lblTipoUsuario.setTextColor(Color.WHITE);
        lblTipoUsuario.setTextSize(13);
        //Iconos
        iconMenuPrincipal = (ImageButton) findViewById(R.id.iconRegresarMenu);
        iconMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regresarMenu();
            }
        });
        iconMenuPerfil = (ImageButton) findViewById(R.id.iconoDatosPerfil);
        iconMenuPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Perfil perfil = perfilController.detalle(id, contexto);
                funciones.alertasDialog("Info perfil", iP.mostrarDetalles(perfil));
            }
        });
        cargarDatoPerfil();
    }

    public void cargarRecycler(){
        //Recycler
        recycler = (RecyclerView) findViewById(R.id.recycler);
        linManager = new LinearLayoutManager(contexto);
        recycler.setLayoutManager(linManager);
    }

    public void cargarDatoPerfil(){
        try {
            Perfil perfil = perfilController.detalle(id,this.getApplicationContext());
            String nombres = perfil.getNombres();
            String tipoUsuario = perfil.getTipoUsuario();
            lblFullName.setText(nombres);
            lblTipoUsuario.setText(tipoUsuario);
        }catch (Exception ex){
            Log.e("Principal(cargarDatosPerfil)", "Error: " + ex.getMessage());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            regresarMenu();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void regresarMenu(){
        Intent principal = new Intent(ListaEstudiantes.this,Principal.class);
        principal.putExtra("id", id);
        startActivity(principal);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        finish();
    }

}

package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.cloudnets.cloudacademic.Helpers.Funciones;
import com.cloudnets.cloudacademic.R;
import java.util.ArrayList;

public class Colegios extends Activity {

    //Elementos de la vista
    public Spinner spColegios;
    public Button butAvanzar;
    public String institucion = "";

    //Clase de funciones varias
    private Funciones funciones = new Funciones(this);

    //Contexto general de la aplicacion
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colegios);
        inicializarElementosVista();
        listarColegios();
        inicializarContexto();
    }

    public void inicializarContexto(){
        funciones = new Funciones(context);
    }

    public void inicializarElementosVista(){
        spColegios = (Spinner) findViewById(R.id.spColegios);
        spColegios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                institucion = spColegios.getSelectedItem().toString();
            }@Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        butAvanzar = (Button) findViewById(R.id.butAvanzar);
        butAvanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avanzar();
            }
        });
    }

    public void listarColegios(){
        ArrayList<String> listaColegios = new ArrayList<>();
        listaColegios.add("Seleccione una institucion");
        listaColegios.add("Royal School");
        listaColegios.add("Liceo de Cervantes");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaColegios);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColegios.setAdapter(arrayAdapter);
    }

    public void avanzar(){
        String colegioSel;
        colegioSel = spColegios.getSelectedItem().toString();
        if(!colegioSel.equalsIgnoreCase("Seleccione una institucion")){
            Intent login = new Intent(Colegios.this,Login.class);
            startActivity(login);
        }else{
            funciones.alertasDialog(getString(R.string.error_4),getString(R.string.mensaje_alerta_4));
        }
    }

}

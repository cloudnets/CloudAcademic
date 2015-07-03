package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import com.cloudnets.cloudacademic.Models.Institucion;
import com.cloudnets.cloudacademic.R;

import java.util.ArrayList;

public class Colegios extends Activity {

    private AutoCompleteTextView txtColegios;
    private ImageButton butBuscar;
    private Button butAvanzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colegios);
        inicializarElementosVista();
        agregarColegios();
    }

    public void inicializarElementosVista(){
        txtColegios = (AutoCompleteTextView) findViewById(R.id.txtColegios);
        butBuscar = (ImageButton) findViewById(R.id.butBuscar);
        butBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
        butAvanzar = (Button) findViewById(R.id.butAvanzar);
        butAvanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }

    public void agregarColegios(){
        Institucion col1 = new Institucion("Royal School");
        Institucion col2 = new Institucion("Liceo de Cervantes");
        ArrayList<String> listaColegios = new ArrayList<String>();
        listaColegios.add(col1.getNombre());
        listaColegios.add(col2.getNombre());
        ArrayAdapter<String> lista = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaColegios);
        txtColegios.setAdapter(lista);
    }

    public void avanzar(){
        String colegios = txtColegios.getText().toString();
        if(colegios.equalsIgnoreCase("")){

        }
    }

}

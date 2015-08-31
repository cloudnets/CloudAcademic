package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.cloudnets.cloudacademic.Controllers.CursoController;
import com.cloudnets.cloudacademic.Controllers.EstudianteController;
import com.cloudnets.cloudacademic.Controllers.PerfilController;
import com.cloudnets.cloudacademic.Helpers.Funciones;
import com.cloudnets.cloudacademic.Implementacion.ImplementacionPerfil;
import com.cloudnets.cloudacademic.Models.Curso;
import com.cloudnets.cloudacademic.Models.Estudiante;
import com.cloudnets.cloudacademic.Models.Perfil;
import com.cloudnets.cloudacademic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListaEstudiantes extends Activity {
    //Contexto general de la aplicacion
    private Context contexto = this;
    //Controladores
    private EstudianteController estudianteController = new EstudianteController();
    private PerfilController perfilController = new PerfilController();
    private CursoController cursoController = new CursoController();
    //Elementos de la vista
    private TextView lblFullName;
    private TextView lblTipoUsuario;
    ImageButton iconMenuPrincipal;
    ImageButton iconMenuPerfil;
    LinearLayout contenedorListaTarjetas;
    Spinner spFiltro;
    //Controlador de la clase perfil
    private ImplementacionPerfil iP = new ImplementacionPerfil();
    private Funciones funciones = new Funciones(contexto);
    //ID usuario actvio
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lista_estudiantes);
        idUser();
        inicializarElementosEstudiantes();
        //Spinner filtro
        spFiltro = (Spinner)findViewById(R.id.spCursos);
        spFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String nomCuros = spFiltro.getSelectedItem().toString();
                crearCardEstudiantes(nomCuros);
            }

            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        cargarFiltro();
    }

    public void cargarFiltro(){
        List<Curso> listaCurso = cursoController.obtenerCursos(contexto);
        ArrayList<String> listaNomCursos = new ArrayList<>();
        for (int i = 0; i < listaCurso.size(); i++) {
            listaNomCursos.add(listaCurso.get(i).getDescripcion());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(contexto, android.R.layout.simple_list_item_1, listaNomCursos);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFiltro.setAdapter(arrayAdapter);
    }

    protected void onResume() {
        super.onResume();
        cargarFiltro();
    }

    public void crearCardEstudiantes(String curso){
        List<Estudiante> lista = estudianteController.obtenerEstudianteCurso(curso, contexto);
        if(!lista.isEmpty()){
            contenedorListaTarjetas.removeAllViews();
            for (int i = 0; i < lista.size(); i++) {
                Estudiante est = lista.get(i);
                String nombre = est.nombreCompleto();
                int id = est.getId();
                String identificacion = est.getIdentificacion();
                CardView tarjetaEst = crearTarjeta();
                LinearLayout contenedorHorizontal = crearContenedorHorizontal();
                RelativeLayout contenedorFoto = crearContenedorFoto();
                CircleImageView foto = crearImagenPerfil(id);
                LinearLayout contenedorVertical = crearContenedorVertical();
                TextView etiquetaEst = crearEtiquetaNombre(nombre, id);
                TextView etiquetaDet = crearEtiquetaDetalle(identificacion);
                Switch suicheAsistencia = crearSuiche(id);
                Space espacio = crearEspacio();
                contenedorFoto.addView(foto);
                contenedorVertical.addView(etiquetaEst);
                contenedorVertical.addView(etiquetaDet);
                contenedorVertical.addView(suicheAsistencia);
                contenedorHorizontal.addView(contenedorFoto);
                contenedorHorizontal.addView(contenedorVertical);
                tarjetaEst.addView(contenedorHorizontal);
                contenedorListaTarjetas.addView(tarjetaEst);
                contenedorListaTarjetas.addView(espacio);
            }
        }
    }

    public Space crearEspacio(){
        final Space espacio = new Space(contexto);
        espacio.setMinimumHeight(30);
        return espacio;
    }

    public CardView crearTarjeta(){
        final CardView cardEstDet = new CardView(contexto);
        cardEstDet.setCardElevation(10);
        cardEstDet.setCardBackgroundColor(Color.WHITE);
        cardEstDet.setRadius(10);
        cardEstDet.setMinimumHeight(CardView.LayoutParams.WRAP_CONTENT);
        cardEstDet.setMinimumWidth(CardView.LayoutParams.MATCH_PARENT);
        return cardEstDet;
    }

    public TextView crearEtiquetaNombre(String nomEstudiante, int idEstudiante){
        final TextView etiquetaEst = new TextView(contexto);
        etiquetaEst.setId(R.id.lblTitulo + idEstudiante);
        etiquetaEst.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        etiquetaEst.setTextColor(Color.rgb(102, 102, 102));
        etiquetaEst.setText(nomEstudiante);
        return etiquetaEst;
    }

    public TextView crearEtiquetaDetalle(String identificacion){
        final TextView etiquetaDetalle = new TextView(contexto);
        etiquetaDetalle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        etiquetaDetalle.setTextColor(Color.rgb(102, 102, 102));
        etiquetaDetalle.setText("Identificacion: " + identificacion);
        return etiquetaDetalle;
    }

    public RelativeLayout crearContenedorFoto(){
        final RelativeLayout contenedor = new RelativeLayout(contexto);
        contenedor.setMinimumWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        contenedor.setMinimumHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        contenedor.setPadding(10, 10, 10, 10);
        return contenedor;
    }

    public CircleImageView crearImagenPerfil(int id){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        params.gravity = Gravity.CENTER_VERTICAL;
        final CircleImageView fotoPerfil = new CircleImageView(contexto);
        fotoPerfil.setId(R.id.iconoDatosPerfil + id);
        fotoPerfil.setBorderColor(Color.WHITE);
        fotoPerfil.setBorderWidth(4);
        fotoPerfil.setLayoutParams(params);
        Picasso.with(contexto)
                .load(R.drawable.student)
                .error(R.drawable.user)
                .resize(190,190)
                .centerCrop()
                .into(fotoPerfil);
        return fotoPerfil;
    }

    public LinearLayout crearContenedorVertical(){
        final LinearLayout contenedor = new LinearLayout(contexto);
        contenedor.setOrientation(LinearLayout.VERTICAL);
        contenedor.setMinimumWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        contenedor.setMinimumHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        contenedor.setGravity(Gravity.CENTER_VERTICAL);
        return contenedor;
    }

    public LinearLayout crearContenedorHorizontal(){
        final LinearLayout contenedor = new LinearLayout(contexto);
        contenedor.setOrientation(LinearLayout.HORIZONTAL);
        contenedor.setMinimumWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        contenedor.setMinimumHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        contenedor.setGravity(Gravity.CENTER_VERTICAL);
        return contenedor;
    }

    public Switch crearSuiche(int id) {
        final Switch asistencia = new Switch(contexto);
        asistencia.setId(id);
        asistencia.setMinimumWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        asistencia.setMinimumHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        asistencia.setPadding(10, 0, 0, 0);
        asistencia.setChecked(true);
        return asistencia;
    }

    public void idUser(){
        Bundle b = getIntent().getExtras();
        id = b.getInt("id");
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
        //Scroll
        contenedorListaTarjetas = (LinearLayout) findViewById(R.id.contenedor_tarjetas);
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

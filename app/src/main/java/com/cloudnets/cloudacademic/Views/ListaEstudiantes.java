package com.cloudnets.cloudacademic.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import com.cloudnets.cloudacademic.Controllers.*;
import com.cloudnets.cloudacademic.Helpers.Funciones;
import com.cloudnets.cloudacademic.Implementacion.ImplementacionPerfil;
import com.cloudnets.cloudacademic.Models.*;
import com.cloudnets.cloudacademic.R;
import com.google.gson.Gson;
import com.loopj.android.http.HttpGet;
import com.squareup.picasso.Picasso;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class ListaEstudiantes extends Activity {
    //Contexto general de la aplicacion
    private Context contexto = this;
    //Controladores
    private PerfilController perfilController = new PerfilController();
    private CursoController cursoController = new CursoController();
    private EstudianteController estudianteController = new EstudianteController();
    private ReporteController reporteController = new ReporteController();
    private InasistenciaController inasistenciaController = new InasistenciaController();
    //Elementos de la vista
    private TextView lblFullName;
    private TextView lblTipoUsuario;
    ImageButton iconMenuPrincipal;
    ImageButton iconMenuPerfil;
    Button butGuardar;
    LinearLayout contenedorListaTarjetas;
    Spinner spFiltro;
    //Controlador de la clase perfil
    private ImplementacionPerfil iP = new ImplementacionPerfil();
    private Funciones funciones = new Funciones(contexto);
    //ID usuario actvio
    int id;
    int idCursoSeleccionado;
    String usuarioCoordinador;
    String caso;
    //Array de los alumnos que no asistieron
    ArrayList<Integer> listaAsistencia = new ArrayList<>();

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
        definirOperacion();
    }

    public void definirOperacion(){
        Bundle bundle = getIntent().getExtras();
        boolean push = bundle.getBoolean("push");
        if(push){
            caso = "2";
            cargarDatosPush();
            spFiltro.setVisibility(View.GONE);
        }else{
            caso = "1";
            spFiltro.setVisibility(View.VISIBLE);
            cargarFiltroEstudiantes();
            activarFiltro();
        }
    }

    public void guardarDatos(){
        switch (caso){
            case "1":
                if(idCursoSeleccionado == 0){
                    funciones.alertasDialog("Error","Debe seleccionar un curso antes de guardar los datos");
                }else{
                    iniciarSincronizacion();
                }
                break;
            case "2":
                butGuardar.setText(getString(R.string.sincronizar));
                cargarDatosPush();
                break;
        }
    }

//****************Seccion reportes*************//
    public void cargarReporte(){
        List<ReporteInasistencia> lista = reporteController.obtenerReportes(contexto);
        if(!lista.isEmpty()){
            contenedorListaTarjetas.removeAllViews();
            for (int i = 0; i < lista.size(); i++) {
                ReporteInasistencia reporte = lista.get(i);
                String nombre = reporte.getNombreCompleto();
                int id = reporte.getId();
                String identificacion = reporte.getIdentificacion();
                CardView tarjetaEst = crearTarjeta();
                LinearLayout contenedorHorizontal = crearContenedorHorizontal();
                RelativeLayout contenedorFoto = crearContenedorFoto();
                CircleImageView foto = crearImagenPerfil(id);
                LinearLayout contenedorVertical = crearContenedorVertical();
                TextView etiquetaEst = crearEtiquetaNombre(nombre);
                TextView etiquetaDet = crearEtiquetaDetalle(identificacion);
                Space espacio = crearEspacio();
                contenedorFoto.addView(foto);
                contenedorVertical.addView(etiquetaEst);
                contenedorVertical.addView(etiquetaDet);
                contenedorHorizontal.addView(contenedorFoto);
                contenedorHorizontal.addView(contenedorVertical);
                tarjetaEst.addView(contenedorHorizontal);
                contenedorListaTarjetas.addView(tarjetaEst);
                contenedorListaTarjetas.addView(espacio);
            }
        }
    }

    public void cargarDatosPush(){
        descargarReportes descargarReportes = new descargarReportes();
        descargarReportes.execute();
    }
    private class descargarReportes extends AsyncTask<String, Integer, Boolean> {
        protected void onPreExecute(){
            funciones.alertasAsincronicas(getString(R.string.descargando), getString(R.string.mensaje_descarga));
        }
        protected Boolean doInBackground(String... params) {
            boolean res;
            String ipURL = getString(R.string.url_con);
            Perfil perfil = perfilController.buscarPerfil(contexto);
            String codigo = perfil.getToken();
            res = obtenerReportes(codigo, ipURL);
            return res;
        }
        public void onPostExecute(Boolean resul){
            funciones.cancelarDialog();
            if(resul){
                cargarReporte();
            }else{
                funciones.alertasDialog(getString(R.string.error_5), getString(R.string.mensaje_alerta_5));
            }
        }
    }

    public Boolean obtenerReportes(String cod_coordinador, String ipURL){
        boolean res = false;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getCursos = new HttpGet(ipURL+"/areas/api/obtener/inasistencias?user="+cod_coordinador);
        getCursos.setHeader("content-type", "application/json");
        try{
            HttpResponse resp = httpClient.execute(getCursos);
            String response = EntityUtils.toString(resp.getEntity());
            System.out.println(response);
            try {
                JSONObject json = new JSONObject(response);
                boolean success = json.getBoolean("success");
                if(success){
                    JSONArray arrayData = json.getJSONArray("inasistencias");
                    res = guardarReporte(arrayData);
                }
            } catch (JSONException e) {
                Log.i("ListaEstudiantes(obtenerReportes)", "Error JSONException: " + e.toString());
            }
        }catch(Exception e){
            Log.i("ListaEstudiantes(obtenerReportes)", "Error Exception: " + e.toString());
        }
        return res;
    }

    public boolean guardarReporte(JSONArray listaReporte){
        boolean res = true;
        try {
            System.out.println(listaReporte);
            for (int i = 0; i < listaReporte.length(); i++) {
                JSONObject jsonInasistencia = listaReporte.getJSONObject(i);
                ReporteInasistencia reporteInasistencia = new Gson().fromJson(
                        jsonInasistencia.toString(), ReporteInasistencia.class
                );
                res = reporteController.crear(reporteInasistencia, contexto);
                if(!res){
                    res = false;
                    break;
                }
            }
        }catch (Exception ex){
            res = false;
            Log.e("ListaEstudiantes(guardarReporte)", "Error: " + ex.getMessage());
        }
        return res;
    }

//****************Seccion filtro*************//
    public void cargarFiltroEstudiantes(){
        List<Curso> listaCurso = cursoController.obtenerCursos(contexto);
        ArrayList<String> listaNomCursos = new ArrayList<>();
        listaNomCursos.add("Seleccione un curso");
        for (int i = 0; i < listaCurso.size(); i++) {
            listaNomCursos.add(listaCurso.get(i).getDescripcion());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(contexto, android.R.layout.simple_list_item_1, listaNomCursos);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFiltro.setAdapter(arrayAdapter);
    }

    public void activarFiltro(){
        spFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String nomCuros = spFiltro.getSelectedItem().toString();
                idCursoSeleccionado = spFiltro.getSelectedItemPosition();
                if (idCursoSeleccionado != 0) {
                    Curso curso = cursoController.detalle(idCursoSeleccionado, contexto);
                    usuarioCoordinador = curso.getUsucoordinador();
                }
                crearEstudiantes(nomCuros);
            }
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    public void crearEstudiantes(String curso){
        List<Estudiante> lista = estudianteController.obtenerEstudianteCurso(curso, contexto);
        if(!lista.isEmpty()){
            contenedorListaTarjetas.removeAllViews();
            listaAsistencia.clear();
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
                TextView etiquetaEst = crearEtiquetaNombre(nombre);
                TextView etiquetaDet = crearEtiquetaDetalle(identificacion);
                CheckBox suicheAsistencia = crearSuiche(id,tarjetaEst);
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

    public CardView crearTarjeta(){
        final CardView cardEstDet = new CardView(contexto);
        cardEstDet.setCardElevation(10);
        cardEstDet.setCardBackgroundColor(Color.WHITE);
        cardEstDet.setRadius(10);
        cardEstDet.setMinimumHeight(CardView.LayoutParams.WRAP_CONTENT);
        cardEstDet.setMinimumWidth(CardView.LayoutParams.MATCH_PARENT);
        return cardEstDet;
    }

    public TextView crearEtiquetaNombre(String nomEstudiante){
        final TextView etiquetaEst = new TextView(contexto);
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

    public CheckBox crearSuiche(int id, final CardView tarjetaEst) {
        final CheckBox asistencia = new CheckBox(contexto);
        asistencia.setId(R.id.cbBase + id);
        listaAsistencia.add(id);
        asistencia.setChecked(true);
        asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (asistencia.isChecked()) {
                    tarjetaEst.setCardBackgroundColor(Color.WHITE);
                } else {
                    tarjetaEst.setCardBackgroundColor(Color.rgb(255,210,210));
                }
            }
        });
        return asistencia;
    }

    public void idUser(){
        PerfilController perfilController = new PerfilController();
        Perfil perfil = perfilController.buscarPerfil(contexto);
        id = perfil.getId();
    }

    public Space crearEspacio(){
        final Space espacio = new Space(contexto);
        espacio.setMinimumHeight(30);
        return espacio;
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
        //Boton guardar
        butGuardar = (Button) findViewById(R.id.butGuardar);
        butGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });
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
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        finish();
    }

    public List<Inasistencia> generarListaInasistencias(){
        final List<Inasistencia> lista = new ArrayList<>();
        for (int i = 0; i < listaAsistencia.size(); i++) {
            final int idSuiche = listaAsistencia.get(i);
            CheckBox suicheEst = (CheckBox) findViewById(R.id.cbBase+idSuiche);
            if(suicheEst.isChecked()){
                return lista;
            }else{
                Estudiante estudiante = estudianteController.detalle(idSuiche,contexto);
                Inasistencia inasistencia = new Inasistencia();
                inasistencia.setCodgradoasig(estudiante.getCodgradosasig());
                inasistencia.setCodestumatricula(estudiante.getCodestumatricula());
                lista.add(inasistencia);
            }
        }
        return lista;
    }

    public void iniciarSincronizacion(){
        sincronizarAsistencias sincAsis = new sincronizarAsistencias();
        sincAsis.execute();
    }
    private class sincronizarAsistencias extends AsyncTask<String, Integer, Boolean> {
        protected void onPreExecute(){
            funciones.alertasAsincronicas(getString(R.string.validar_1), getString(R.string.validar_2));
        }
        protected Boolean doInBackground(String... params) {
            boolean res = false;
            List<Inasistencia> lista = generarListaInasistencias();
            System.out.println(lista.size());
            if(!lista.isEmpty()){
                for (int i = 0; i < lista.size(); i++) {
                    Inasistencia inasistencia = lista.get(i);
                    res = guardarReporte(inasistencia);
                    if(!res){
                        return false;
                    }
                }
            }
            return res;
        }
        public void onPostExecute(Boolean resul){
            funciones.cancelarDialog();
            if(resul){
                activarNotificacionPush();
                funciones.alertasDialog("Inasistencias guardadas", "Datos sincronizados correctamente");
            }else{
                funciones.alertasDialog("", "Error al enviar.");
            }
        }
    }

    public Boolean guardarReporte(Inasistencia inasistencia){
        String ipURL = getString(R.string.url_con);
        boolean res = false;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost postInasistencia = new HttpPost(ipURL+"/areas/api/inasistencias");
        try{
            List<NameValuePair> parametros = new ArrayList<>();
            parametros.add(new BasicNameValuePair("codestumatricula", inasistencia.getCodestumatricula()));
            parametros.add(new BasicNameValuePair("codgradoasig", inasistencia.getCodgradoasig()));
            postInasistencia.setEntity(new UrlEncodedFormEntity(parametros));
            HttpResponse response = httpClient.execute(postInasistencia);
            String respString = EntityUtils.toString(response.getEntity());
            System.out.println(respString);
            try {
                JSONObject json = new JSONObject(respString);
                boolean success = json.getBoolean("success");
                if(success){
                    res = true;
                }
            } catch (JSONException e) {
                Log.i("ImplementacionLogin(Login)", "Error JSONException: " + e.toString());
            }
        }catch(Exception e){
            Log.i("ListaEstudiante(guardarReporte)","Error Exception: "+e.toString());
        }
        return res;
    }

    /*Funcion que permite enviar la notificacion en caso tal de que las inasistencias
    **se hayan enviado correctamente al servidor del usuario*/
    public void activarNotificacionPush(){
        enviarNotificacion logout = new enviarNotificacion();
        logout.execute();
    }
    private class enviarNotificacion extends AsyncTask<String, Integer, Boolean>{
        protected Boolean doInBackground(String... params) {
            String ipURL = getString(R.string.url_con);
            return enviarNotificacion(ipURL);
        }
        public void onPostExecute(Boolean resul){
            if(resul){
                Log.i("ListaEstudiantes(activarNotificacionPush)","Push");
            }
        }
    }

    public Boolean enviarNotificacion(String ipURL){
        boolean res = false;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet getCursos = new HttpGet(ipURL+"/areas/api/notificaciones?usuario="+usuarioCoordinador);
        getCursos.setHeader("content-type", "application/json");
        try{
            HttpResponse resp = httpClient.execute(getCursos);
            String response = EntityUtils.toString(resp.getEntity());
            try {
                JSONObject json = new JSONObject(response);
                boolean success = json.getBoolean("success");
                if(success){
                    res = true;
                    Log.i("ListaEstudiantes(activarNotificacion)","Se envio correctamente");
                }
            } catch (JSONException e) {
                Log.i("ListaEstudiantes(activarNotificacion)", "Error JSONException: " + e.toString());
            }
        }catch(Exception e){
            Log.i("ListaEstudiantes(activarNotificacion)","Error Exception: "+e.toString());
        }
        return res;
    }

}

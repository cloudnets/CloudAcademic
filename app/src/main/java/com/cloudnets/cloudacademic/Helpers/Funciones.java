package com.cloudnets.cloudacademic.Helpers;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Deimer on 29/06/2015.
 * ----------------------------------------------
 */
public class Funciones {

    //Variable para alerta asincronica
    ProgressDialog dialog;

    private Context contexto;
    public void funciones(){
    }
    public Funciones(Context contexto){
        this.contexto = contexto;
        funciones();
    }

    public void setContext(Context contexto){
        this.contexto = contexto;
    }

    //Metodo para verificar la conectividad a internet
    public boolean hayConexion() {
        boolean res = false;
        if (contexto != null){
            ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            res = netInfo != null && netInfo.isConnectedOrConnecting();
        }
        return res;
    }

    //Funcion que lanza un dialog con un mensaje de alerta y un titulo
    public void alertasDialog(String Titulo, String Alerta){
        final AlertDialog.Builder alertaSimple = new AlertDialog.Builder(contexto);
        alertaSimple.setTitle(Titulo);
        alertaSimple.setMessage(Alerta);
        alertaSimple.setPositiveButton("Aceptar",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
            }
        });
        alertaSimple.show();
    }

    //Funcion que lanza una alerta en un toast con un tiempo definido
    public void alertasToast(String mensaje){
        Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void alertasAsincronicas(String titulo,String mensaje){
        dialog = new ProgressDialog(contexto);
        dialog.setCancelable(false);
        dialog.setTitle(titulo);
        dialog.setMessage(mensaje);
        dialog.show();
    }

    public void cancelarDialog(){
        dialog.cancel();
    }

    //Funcion que genera un codigo escogiendo 8 caracteres al azar de un array
    public String generarToken(){
        String codigo = "";
        String[] elementos = {"0","1","2","3","4","5","6","7","8","9",
                "A","B","C","D","E","F","G","H","I","J",
                "K","L","M","N","O","P","Q","R","S","T",
                "U","V","W","X","Y","Z","a","b","c","d",
                "e","f","g","h","i","j","k","l","m","n",
                "o","p","q","r","s","t","u","v","w","x",
                "y","z"};
        List<String> arrayElementos = Arrays.asList(elementos);
        for(int i=0;i<10;i++){
            int pos = (int)(Math.random()*36);
            codigo += arrayElementos.get(pos);
        }
        Log.i("codigo", codigo);
        return codigo;
    }

    public String obtenerIMEI(){
        String imei;
        TelephonyManager imeiEquipo = (TelephonyManager) contexto.getSystemService(Context.TELEPHONY_SERVICE);
        imei = imeiEquipo.getDeviceId();
        return imei;
    }

}
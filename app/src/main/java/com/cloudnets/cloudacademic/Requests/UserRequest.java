package com.cloudnets.cloudacademic.Requests;

import android.content.Context;
import android.util.Log;
import com.cloudnets.cloudacademic.Models.Proceso;
import com.cloudnets.cloudacademic.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Creado por Deimer Villa on 30/06/2015.
 * ----------------------------------------------------
 * Clase de peticiones request del objeto usuario:
 * Esta clase es la encargada de hacer las peticiones
 * http request del modelo usuario, asi mismo tambien se encarga
 * de la validacion y logueo del usuario.
 */
public class UserRequest {

    //Contexto de la clase para controlar las funciones
    public Context contexto;

    public void userRequest(){
    }

    public UserRequest(Context contexto){
        this.contexto = contexto;
        userRequest();
    }

    /*public void setContext(Context contexto){ this.contexto = contexto; }*/

    public Proceso Login(String user, String pass, String ipURL){
        Proceso proceso = new Proceso();
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost postLogin = new HttpPost(ipURL+"/areas/api/usuario");
        postLogin.setHeader("content-type", "application/json");
        try{
            try {
                JSONObject jsonUser = new JSONObject();
                jsonUser.put("user",user);
                jsonUser.put("pass",pass);
                StringEntity entity = new StringEntity(jsonUser.toString());
                postLogin.setEntity(entity);
                HttpResponse resp = httpClient.execute(postLogin);
                String respStr = EntityUtils.toString(resp.getEntity());
                System.out.println(respStr);
                if(respStr.equals("true")){
                    proceso.setResultado(false);
                    proceso.setTitulo("Error");
                    proceso.setMensaje("Credenciales incorrectas");
                }else{
                    JSONObject json = new JSONObject(respStr);
                    String usuario = (json.getString("usuario"));
                    String password = (json.getString("pass"));
                    String identificacion = (json.getString("identificacion"));
                    String nombres = (json.getString("nombres"));
                    String apellidos = (json.getString("apellidos"));
                    String email = (json.getString("email"));
                    String cod_perfil = (json.getString("cod_perfil"));
                    String cod_tipoUsuario = (json.getString("cod_tipoUsuario"));
                    String nom_tipoUsuario = (json.getString("nom_tipoUsuario"));
                    boolean success = json.getBoolean("success");
                    Log.i("Datos json","user:"+usuario+",pass:"+password+",identificacion:"+identificacion+",nombres:"+nombres+",apellidos:"+apellidos+",email:"+email+",cod_perfil:"+cod_perfil+",cod_tipoUsuario:"+cod_tipoUsuario+",nom_tipoUsuario:"+nom_tipoUsuario);
                    proceso.setResultado(success);
                    proceso.setTitulo("Conexion");
                    proceso.setMensaje("Validacion exitosa");
                }
            } catch (JSONException e) {
                proceso.setResultado(false);
                proceso.setTitulo("userRequest(Login)");
                proceso.setMensaje("Error JSONException: "+e.toString());
                Log.i("userRequest(Login)","Error JSONException: "+e.toString());
            }
        }catch(Exception e){
            proceso.setResultado(false);
            proceso.setTitulo("userRequest(Login)");
            proceso.setMensaje("Error Exception: "+e.toString());
            Log.i("userRequest(Login)", "Error Exception: " + e.toString());
        }
        return proceso;
    }

}

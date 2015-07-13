package com.cloudnets.cloudacademic.Requests;

import android.content.Context;
import android.util.Log;
import com.cloudnets.cloudacademic.Controllers.UsuarioController;
import com.cloudnets.cloudacademic.Implementacion.ImplementacionUsuario;
import com.cloudnets.cloudacademic.Models.Proceso;
import com.cloudnets.cloudacademic.Models.Usuario;
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
public class RequestLogin {

    //Contexto de la clase para controlar las funciones
    public Context contexto;
    //Variable privada para el acceso al controlador del usuario
    private UsuarioController usuarioController = new UsuarioController();

    public void validacion(){}

    public RequestLogin(Context contexto){
        this.contexto = contexto;
        validacion();
    }

    public void setContext(Context contexto){
        this.contexto = contexto;
    }

    public Boolean guardarUsuario(JSONObject json){
        boolean res = false;
        ImplementacionUsuario iU = new ImplementacionUsuario();
        try {
            Usuario usuario = new Usuario();
            usuario.setIdentificacion(json.getString("identificacion"));
            usuario.setUsuario(json.getString("usuario"));
            usuario.setPassword(json.getString("pass"));
            usuario.setNombres(json.getString("nombres"));
            usuario.setApellidos(json.getString("apellidos"));
            usuario.setEmail(json.getString("email"));
            usuario.setPerfil(json.getString("perfil"));
            usuario.setTipoUsuario(json.getString("tipoUsuario"));
            res = usuarioController.crear(usuario, contexto);
            Log.i("Datos Usuario", iU.mostrarDetalles(usuario));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

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
                JSONObject json = new JSONObject(respStr);
                boolean success = json.getBoolean("success");
                if(success){
                    boolean res = guardarUsuario(json);
                    proceso.setResultado(res);
                    proceso.setTitulo("Conexion");
                    proceso.setMensaje("Login exitosa");
                }else{
                    proceso.setResultado(success);
                    proceso.setTitulo("Sin conectar");
                    proceso.setMensaje("El usuario o la contraseña no son validos.");
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

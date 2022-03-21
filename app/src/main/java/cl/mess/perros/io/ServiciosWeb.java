package cl.mess.perros.io;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ServiciosWeb {
    public String traerPerros(){
        URL url;
        String linea;
        int respuesta;
        StringBuilder result = null;
        try {
            url = new URL("https://dog.ceo/api/breeds/list");
            Log.i("URL:", url.toString());
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            respuesta = conexion.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conexion.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (Exception e) {
            Log.e("ERROR WS", e.getMessage());
        }
        assert result != null;
        return result.toString();
    }

    public String traerImagenesPerros(String perro){
        URL url;
        String linea;
        int respuesta;
        String direccion = "https://dog.ceo/api/breed/"+perro+"/images";
        StringBuilder result = null;
        try {
            url = new URL(direccion);
            Log.i("URL:", url.toString());
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            respuesta = conexion.getResponseCode();
            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conexion.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (Exception e) {
            Log.e("ERROR WS", e.getMessage());
        }
        assert result != null;
        return result.toString();
    }
}

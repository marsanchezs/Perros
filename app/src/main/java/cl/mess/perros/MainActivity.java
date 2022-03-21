package cl.mess.perros;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cl.mess.perros.io.ServiciosWeb;
import cl.mess.perros.modelo.Perro;

public class MainActivity extends AppCompatActivity {

    private ListView lvPerros;
    private final ServiciosWeb servicioWeb = new ServiciosWeb();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPerros = (ListView) findViewById(R.id.lvPerrosMA);
        cargarPerros();
        capturarPerro();
    }

    //MÉTODOS
    public void capturarPerro(){
        lvPerros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String perro = String.valueOf(lvPerros.getItemAtPosition(position));
                traerImagenesPerros(perro);
                //Toast.makeText(getApplicationContext(), perro, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void traerImagenesPerros(String raza){
        Thread miHilo = new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                ArrayList<String> imagenes = new ArrayList<>();
                try {
                    String respuestaPerros = servicioWeb.traerImagenesPerros(raza);
                    Log.i("IMÁGENES PERROS MA", respuestaPerros);
                    imagenes = generarListaImagenesPerros(respuestaPerros);
                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage());
                }
                final ArrayList<String> imagenes2 = imagenes;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Perro perro = generarPerro(raza, imagenes2);
                        Intent imagenesPerro = new Intent(MainActivity.this, ImagenesPerro.class);
                        imagenesPerro.putExtra("perro", perro);
                        startActivity(imagenesPerro);
                        finish();
                    }
                });
            }
        };
        miHilo.start();
    }

    private Perro generarPerro(String raza, ArrayList<String> lista){
        Perro perro = new Perro();
        perro.setRaza(raza);
        perro.setSubraza("");
        perro.setImagenes(lista);
        return perro;
    }

    private ArrayList<String> generarListaImagenesPerros(String perros) throws JSONException {
        Log.e("JSON PERROS", perros);
        ArrayList<String> lista = new ArrayList<>();
        JSONObject objetoJSON = new JSONObject(perros);
        JSONArray jsonArray = objetoJSON.getJSONArray("message");
        for(int i = 0; i < jsonArray.length(); i++){
            lista.add(jsonArray.getString(i));
        }
        System.out.println("LISTADO IMÁGENES DE PERROS: "+lista.toString());
        return lista;
    }

    private void cargarPerros(){
        Thread miHilo = new Thread() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                ArrayList<String> perros = new ArrayList<>();
                try {
                    String respuestaPerros = servicioWeb.traerPerros();
                    Log.i("RESPUESTA PERROS MA", respuestaPerros);
                    perros = generarListaPerros(respuestaPerros);
                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage());
                }
                final ArrayList<String> razas = perros;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] arrayRazas = new String[razas.size()];
                        arrayRazas = razas.toArray(arrayRazas);
                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayRazas);
                        lvPerros.setAdapter(adaptador);
                    }
                });
            }
        };
        miHilo.start();
    }

    private ArrayList<String> generarListaPerros(String perros) throws JSONException {
        Log.e("JSON PERROS", perros);
        ArrayList<String> lista = new ArrayList<>();
        JSONObject objetoJSON = new JSONObject(perros);
        JSONArray jsonArray = objetoJSON.getJSONArray("message");
        for(int i = 0; i < jsonArray.length(); i++){
            lista.add(jsonArray.getString(i));
        }
        System.out.println("LISTADO DE PERROS: "+lista.toString());
        return lista;
    }


}
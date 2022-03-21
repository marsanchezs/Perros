package cl.mess.perros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import cl.mess.perros.modelo.Perro;
import cl.mess.perros.ui.AdaptadorImagenes;

public class ImagenesPerro extends AppCompatActivity {

    private Perro perro;
    private TextView txtRaza;
    private RecyclerView rvImagenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes_perro);

        perro = (Perro) Objects.requireNonNull(getIntent().getExtras()).getSerializable("perro");
        System.out.println("RAZA: "+perro.getRaza());
        System.out.println("IMÁGENES: "+perro.getImagenes().toString());
        ImageView imgAtras = (ImageView) findViewById(R.id.imgAtrasIP);
        txtRaza = (TextView) findViewById(R.id.txtRazaIP);
        rvImagenes = (RecyclerView) findViewById(R.id.rvImagenesPerrosIP);
        //rvImagenes.setLayoutManager(new LinearLayoutManager(this));
        rvImagenes.setLayoutManager(new GridLayoutManager(this, 3));

        imgAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vista) {
                Intent main = new Intent(ImagenesPerro.this, MainActivity.class);
                startActivity(main);
            }
        });

        cargar();
    }

    //MÉTODOS
    private void cargar(){
        txtRaza.setText(perro.getRaza());
        AdaptadorImagenes adaptador = new AdaptadorImagenes(perro.getImagenes());
        rvImagenes.setAdapter(adaptador);
    }
}
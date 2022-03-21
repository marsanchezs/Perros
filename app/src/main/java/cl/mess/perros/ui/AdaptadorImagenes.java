package cl.mess.perros.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cl.mess.perros.R;

public class AdaptadorImagenes extends RecyclerView.Adapter<AdaptadorImagenes.ViewHolder>{
    private ArrayList<String> imagenes;

    public AdaptadorImagenes(ArrayList<String> lista) {
        this.imagenes = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagenes_perro, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imagen = imagenes.get(position);
        Picasso.get().load(imagen).into(holder.imgPerro);
    }

    @Override
    public int getItemCount() {
        return imagenes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPerro;
        public ViewHolder(View v) {
            super(v);
            imgPerro = (ImageView) v.findViewById(R.id.imgImagenPerroIP);
        }
    }
}

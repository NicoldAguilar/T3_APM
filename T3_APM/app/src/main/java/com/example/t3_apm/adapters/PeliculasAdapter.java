package com.example.t3_apm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_apm.R;
import com.example.t3_apm.entidades.PeliEnt;

import java.util.List;

public class PeliculasAdapter extends RecyclerView.Adapter {
    List<PeliEnt> datos;
    public PeliculasAdapter(List<PeliEnt> datos) {
        this.datos=datos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_pelicula,parent,false);
        return new PeliculasAdapter.PeliculasAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView tvnombre = holder.itemView.findViewById(R.id.tvnombre);
        tvnombre.setText(datos.get(position).nombre);

        TextView tvdsc = holder.itemView.findViewById(R.id.tvsinopsis);
        tvdsc.setText(datos.get(position).sinopsis);

        TextView tvurl = holder.itemView.findViewById(R.id.tvimgUrl);
        tvdsc.setText(datos.get(position).imgURL);

        /*ImageView ivImg = holder.itemView.findViewById(R.id.ivAnimeImg);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/02/23/13/05/avatar-2092113_1280.png").into(ivImg);*/

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    static class PeliculasAdapterViewHolder extends RecyclerView.ViewHolder{

        public PeliculasAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

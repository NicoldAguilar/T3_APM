package com.example.t3_apm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_apm.R;
import com.example.t3_apm.entidades.AnimeEnt;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter {

    List<AnimeEnt> datos;
    public AnimeAdapter(List<AnimeEnt> datos) {
        this.datos=datos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_anime,parent,false);
        return new AnimeAdapter.AnimeAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView tvnombre = holder.itemView.findViewById(R.id.tvnombre);
        tvnombre.setText(datos.get(position).nombre);

        TextView tvdsc = holder.itemView.findViewById(R.id.tvdescripcion);
        tvdsc.setText(datos.get(position).descripcion);

        /*ImageView ivImg = holder.itemView.findViewById(R.id.ivAnimeImg);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/02/23/13/05/avatar-2092113_1280.png").into(ivImg);*/

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    static class AnimeAdapterViewHolder extends RecyclerView.ViewHolder{

        public AnimeAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

package com.example.t3_apm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_apm.R;
import com.example.t3_apm.entidades.ContactEnt;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter {

    List<ContactEnt> datos;
    public ContactAdapter(List<ContactEnt> datos) {
        this.datos=datos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_contact,parent,false);
        return new ContactAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView tvnombre = holder.itemView.findViewById(R.id.tvnombre);
        tvnombre.setText(datos.get(position).nombre);

        TextView tvapellido = holder.itemView.findViewById(R.id.tvapellido);
        tvapellido.setText(datos.get(position).apellido);

        ImageView ivContacImg = holder.itemView.findViewById(R.id.ivContacImg);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/02/23/13/05/avatar-2092113_1280.png").into(ivContacImg);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    static class ContactAdapterViewHolder extends RecyclerView.ViewHolder{

        public ContactAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

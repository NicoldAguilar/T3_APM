package com.example.t3_apm;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_apm.adapters.ContactAdapter;
import com.example.t3_apm.entidades.ContactEnt;
import com.example.t3_apm.servicios.ContactService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarContacts extends AppCompatActivity {

    private RecyclerView rvContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contacts);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContactService services = retrofit.create(ContactService.class);
        services.getListContact().enqueue(new Callback<List<ContactEnt>>() {
            @Override
            public void onResponse(Call<List<ContactEnt>> call, Response<List<ContactEnt>> response) {
                List<ContactEnt> datos = response.body();
                rvContact=findViewById(R.id.rvContactosLista);
                rvContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvContact.setAdapter(new ContactAdapter(datos));
                Log.i("MAIN_APP", "funciona");
            }

            @Override
            public void onFailure(Call<List<ContactEnt>> call, Throwable t) {
                Log.i("MAIN_APP", "no funciona");
            }
        });

    }
}
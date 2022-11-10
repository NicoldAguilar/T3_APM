package com.example.t3_apm;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t3_apm.adapters.AnimeAdapter;
import com.example.t3_apm.entidades.AnimeEnt;
import com.example.t3_apm.servicios.AnimeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarAnimesActivity extends AppCompatActivity {

    private RecyclerView rvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_animes);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AnimeService services = retrofit.create(AnimeService.class);
        services.getListContact().enqueue(new Callback<List<AnimeEnt>>() {
            @Override
            public void onResponse(Call<List<AnimeEnt>> call, Response<List<AnimeEnt>> response) {
                List<AnimeEnt> datos = response.body();
                rvContact=findViewById(R.id.rvAnimeLista);
                rvContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvContact.setAdapter(new AnimeAdapter(datos));
                Log.i("MAIN_APP", "funciona");
            }

            @Override
            public void onFailure(Call<List<AnimeEnt>> call, Throwable t) {
                Log.i("MAIN_APP", "no funciona");
            }
        });
    }
}
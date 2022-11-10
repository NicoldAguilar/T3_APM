package com.example.t3_apm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.t3_apm.entidades.AnimeEnt;
import com.example.t3_apm.servicios.AnimeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActualizarAnime extends AppCompatActivity {

    EditText etNombre2, etDescripcion2, etimgURL2, etID;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_anime);

        etNombre2 = findViewById(R.id.etNombre2);
        etDescripcion2 = findViewById(R.id.etDescripcion2);
        etimgURL2 = findViewById(R.id.etimgURL2);

        etID = findViewById(R.id.etID);

        button = findViewById(R.id.btnEnviar2);
        button.setOnClickListener(v -> {
            onClick(v);
        });
    }
    private void onClick(View v) {
        actualizar(v);
        Intent intent = new Intent(this, AnimeActivitu.class);
        startActivity(intent);

    }

    public void actualizar(View v) {
        String id = etID.getText().toString();
        String name2 = etNombre2.getText().toString();
        String dsc2 = etDescripcion2.getText().toString();
        String imgURL2 = etimgURL2.getText().toString();
        AnimeEnt contactosrg = new AnimeEnt();
        contactosrg.nombre = name2;
        contactosrg.descripcion = dsc2;
        contactosrg.imgURL = imgURL2;
        contactosrg.id = Integer.parseInt(id);
        updateRetrofit(contactosrg, Integer.parseInt(id));
    }

    public void updateRetrofit(AnimeEnt contactosrg, int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AnimeService service = retrofit.create(AnimeService.class);
        service.update(contactosrg, id).enqueue(new Callback<AnimeEnt>() {
            @Override
            public void onResponse(Call<AnimeEnt> call, Response<AnimeEnt> response) {
                AnimeEnt data = response.body();
            }

            @Override
            public void onFailure(Call<AnimeEnt> call, Throwable t) {

            }
        });
    }
}
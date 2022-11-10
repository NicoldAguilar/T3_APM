package com.example.t3_apm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class FormAnimeActivity extends AppCompatActivity {

    EditText etNombre, etDescripcion, etimgURL;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_anime);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etimgURL = findViewById(R.id.etimgURL);

        button = findViewById(R.id.btnEnviar);
        button.setOnClickListener(v -> {
            onClick(v);
        });
    }

    private void onClick(View v) {
        enviar(v);
        Intent intent = new Intent(this, AnimeActivitu.class);
        startActivity(intent);

    }

    public void enviar(View v) {
        String nombres = etNombre.getText().toString();
        String dsc = etDescripcion.getText().toString();
        String imgURL = etimgURL.getText().toString();
        AnimeEnt animerg = new AnimeEnt();
        animerg.nombre = nombres;
        animerg.descripcion = dsc;
        animerg.imgURL = imgURL;
        postRetrofit(animerg);
    }

    public void postRetrofit(AnimeEnt contactosrg) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AnimeService service = retrofit.create(AnimeService.class);
        service.create(contactosrg).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("MAIN_APP", "RESPONSE" + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}

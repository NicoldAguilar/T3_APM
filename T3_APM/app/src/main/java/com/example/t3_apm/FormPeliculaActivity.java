package com.example.t3_apm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.t3_apm.adapters.AnimeAdapter;
import com.example.t3_apm.entidades.AnimeEnt;
import com.example.t3_apm.entidades.ContactEnt;
import com.example.t3_apm.servicios.AnimeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormPeliculaActivity extends AppCompatActivity {

    EditText etNombre, etSinopsis, etimgURL,etID, etID2, etNombre2, etSinopsis2, etimgURL2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pelicula);

        etNombre = findViewById(R.id.etNombre);
        etSinopsis = findViewById(R.id.etSinopsis);
        etimgURL = findViewById(R.id.etimgURL);

        etNombre2 = findViewById(R.id.etNombre2);
        etSinopsis2 = findViewById(R.id.etSinopsis2);
        etimgURL2 = findViewById(R.id.etimgURL2);

        etID = findViewById(R.id.etID);
        etID2 = findViewById(R.id.etID2);

        button = findViewById(R.id.btnSiguiente);
        button.setOnClickListener(v -> {
            onClick(v);
        });
    }
    private void onClick(View v) {
        enviar(v);
        Intent intent = new Intent(this, PeliculaActivity.class);
        startActivity(intent);

    }

    public void enviar(View v) {
        String nombres = etNombre.getText().toString();
        String dsc = etSinopsis.getText().toString();
        String imgURL = etimgURL.getText().toString();
        AnimeEnt animerg = new PeliEnt();
        animerg.nombre = nombres;
        animerg.sinopsis = dsc;
        animerg.imgURL = imgURL;
        postRetrofit(animerg);
    }

    public void eliminar(View v) {
        String id = etID.getText().toString();
        deleteRetrofit(Integer.parseInt(id));
    }

    public void actualizar(View v) {
        String id = etID2.getText().toString();
        String name2 = etNombre2.getText().toString();
        String sipsis2 = etSinopsis2.getText().toString();
        String imgurl2 = etSinopsis2.getText().toString();
        ContactEnt contactosrg = new ContactEnt();
        contactosrg.nombre = name2;
        contactosrg.apellido = lastname2;
        contactosrg.id = Integer.parseInt(id);
        updateRetrofit(contactosrg, Integer.parseInt(id));
    }

    public void postRetrofit(AnimeEnt contactosrg) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PeliculaService service = retrofit.create(PeliculaService.class);
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
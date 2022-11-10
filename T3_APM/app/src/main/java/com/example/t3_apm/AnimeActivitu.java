package com.example.t3_apm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AnimeActivitu extends AppCompatActivity {

    private Button button, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_activitu);

        button = findViewById(R.id.btnRegistrarAnime);
        button.setOnClickListener(v -> {
            onClick(v);
        });

        button2 = findViewById(R.id.btnMostrarAnime);
        button2.setOnClickListener(v -> {
            onClick2(v);
        });

        button3 = findViewById(R.id.btnEditarAnime);
        button3.setOnClickListener(v -> {
            onClick3(v);
        });

        button4 = findViewById(R.id.btnAbrirCamara);
        button4.setOnClickListener(v -> {
            onClick4(v);
        });

    }

    private void onClick(View v) {
        Intent intent = new Intent(this, FormAnimeActivity.class);
        startActivity(intent);

    }

    private void onClick2(View v) {
        Intent intent2 = new Intent(this, ListarAnimesActivity.class);
        startActivity(intent2);

    }

    private void onClick3(View v) {
        Intent intent3 = new Intent(this, ActualizarAnime.class);
        startActivity(intent3);

    }

    private void onClick4(View v) {
        Intent intent4 = new Intent(this, CameraActivity.class);
        startActivity(intent4);

    }

}
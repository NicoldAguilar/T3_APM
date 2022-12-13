package com.example.t3_apm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.t3_apm.entidades.PeliEnt;
import com.example.t3_apm.servicios.PeliculaService;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormPeliculaActivity extends AppCompatActivity {

    EditText etNombre, etSinopsis, etimgURL,etID, etID2, etNombre2, etSinopsis2, etimgURL2;
    private Button button, btnTakePhoto, btnTakePhoto2;
    private final static int CAMERA_REQUEST = 1000;
    private ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pelicula);

        btnTakePhoto = findViewById(R.id.btnCamara);
        btnTakePhoto2 = findViewById(R.id.btnCamara2);

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

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    abrirCamara();
                }
                else{
                    requestPermissions(new String[] {Manifest.permission.CAMERA}, 100 );
                }
            }
        });

        btnTakePhoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    abrirCamara();
                }
                else{
                    requestPermissions(new String[] {Manifest.permission.CAMERA}, 100 );
                }
            }
        });
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    private void onClick(View v) {
        enviar(v);
        Intent intent = new Intent(this, PeliculaActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap imageBitmap = null;
        if(requestCode==CAMERA_REQUEST && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ivPhoto.setImageBitmap(imageBitmap);
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        //aca lo de la foto
        //crear mgService con un post + img response
        PeliEnt imgB64 = new PeliEnt();
        imgB64.imgbit64 = encoded;
        postRetrofitIMG(imgB64);

    }

    public void enviar(View v) {
        String nombres = etNombre.getText().toString();
        String dsc = etSinopsis.getText().toString();
        String imgURL = etimgURL.getText().toString();
        PeliEnt pelirg = new PeliEnt();
        pelirg.nombre = nombres;
        pelirg.sinopsis = dsc;
        pelirg.imgURL = imgURL;
        postRetrofit(pelirg);
    }

    public void eliminar(View v) {
        String id = etID.getText().toString();
        deleteRetrofit(Integer.parseInt(id));
    }

    public void actualizar(View v) {
        String id = etID2.getText().toString();
        String name2 = etNombre2.getText().toString();
        String sipsis2 = etSinopsis2.getText().toString();
        String imgurl2 = etimgURL2.getText().toString();
        PeliEnt contactosrg = new PeliEnt();
        contactosrg.nombre = name2;
        contactosrg.sinopsis = sipsis2;
        contactosrg.imgURL = imgurl2;
        contactosrg.id = Integer.parseInt(id);
        updateRetrofit(contactosrg, Integer.parseInt(id));
    }

    public void postRetrofit(PeliEnt contactosrg) {
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

    public void deleteRetrofit(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PeliculaService service = retrofit.create(PeliculaService.class);
        service.delete(id).enqueue(new Callback<PeliEnt>() {
            @Override
            public void onResponse(Call<PeliEnt> call, Response<PeliEnt> response) {
                PeliEnt data = response.body();
            }

            @Override
            public void onFailure(Call<PeliEnt> call, Throwable t) {

            }

        });
    }


    public void updateRetrofit(PeliEnt contactosrg, int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PeliculaService service = retrofit.create(PeliculaService.class);
        service.update(contactosrg, id).enqueue(new Callback<PeliEnt>() {
            @Override
            public void onResponse(Call<PeliEnt> call, Response<PeliEnt> response) {
                PeliEnt data = response.body();
            }

            @Override
            public void onFailure(Call<PeliEnt> call, Throwable t) {

            }
        });
    }

    public void postRetrofitIMG(PeliEnt contactosrg) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.imgur.com/3/image")
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

    public void updateRetrofitIMG(PeliEnt contactosrg, int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.imgur.com/3/image")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PeliculaService service = retrofit.create(PeliculaService.class);
        service.update(contactosrg, id).enqueue(new Callback<PeliEnt>() {
            @Override
            public void onResponse(Call<PeliEnt> call, Response<PeliEnt> response) {
                PeliEnt data = response.body();
            }

            @Override
            public void onFailure(Call<PeliEnt> call, Throwable t) {

            }
        });
    }
}
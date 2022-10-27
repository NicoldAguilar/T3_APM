package com.example.t3_apm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.t3_apm.entidades.ContactEnt;
import com.example.t3_apm.servicios.ContactService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactActivity extends AppCompatActivity {

    EditText etNombre, etApellido, etID,etID2, etNombre2, etApellido2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etNombre2 = findViewById(R.id.etNombre2);
        etApellido2 = findViewById(R.id.etApellido2);

        etID = findViewById(R.id.etID);
        etID2 = findViewById(R.id.etID2);

        button = findViewById(R.id.btnSiguiente);
        button.setOnClickListener(v -> {
            onClick(v);
        });

    }
    private void onClick(View v) {
        Intent intent = new Intent(this, ListarContacts.class);
        startActivity(intent);

    }

    public void enviar(View v) {
        String nombres = etNombre.getText().toString();
        String apellidos = etApellido.getText().toString();
        ContactEnt contactosrg = new ContactEnt();
        contactosrg.nombre = nombres;
        contactosrg.apellido = apellidos;
        postRetrofit(contactosrg);
    }

    public void eliminar(View v) {
        String id = etID.getText().toString();
        deleteRetrofit(Integer.parseInt(id));
    }

    public void actualizar(View v) {
        String id = etID2.getText().toString();
        String name2 = etNombre2.getText().toString();
        String lastname2 = etApellido2.getText().toString();
        ContactEnt contactosrg = new ContactEnt();
        contactosrg.nombre = name2;
        contactosrg.apellido = lastname2;
        contactosrg.id = Integer.parseInt(id);
        updateRetrofit(contactosrg, Integer.parseInt(id));
    }

    public void postRetrofit(ContactEnt contactosrg) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContactService service = retrofit.create(ContactService.class);
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
        ContactService service = retrofit.create(ContactService.class);
        service.delete(id).enqueue(new Callback<ContactEnt>() {
            @Override
            public void onResponse(Call<ContactEnt> call, Response<ContactEnt> response) {
                ContactEnt data = response.body();
            }

            @Override
            public void onFailure(Call<ContactEnt> call, Throwable t) {

            }

        });
    }


    public void updateRetrofit(ContactEnt contactosrg, int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContactService service = retrofit.create(ContactService.class);
        service.update(contactosrg, id).enqueue(new Callback<ContactEnt>() {
            @Override
            public void onResponse(Call<ContactEnt> call, Response<ContactEnt> response) {
                ContactEnt data = response.body();
            }

            @Override
            public void onFailure(Call<ContactEnt> call, Throwable t) {

            }
        });
    }
}
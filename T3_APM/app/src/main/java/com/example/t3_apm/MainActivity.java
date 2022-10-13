package com.example.t3_apm;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    float respuestaOperacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Textos
        EditText numero1 = findViewById(R.id.numero1);
        EditText numero2 = findViewById(R.id.numero2);
        TextView resultado = findViewById(R.id.resultado);

        //Botones
        Button btnsuma = findViewById(R.id.btnsuma);
        btnsuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float num1 = Float.parseFloat(numero1.getText().toString());
                float num2 = Float.parseFloat(numero2.getText().toString());
                respuestaOperacion = num1 + num2;
            }
        });

        Button btnresta = findViewById(R.id.btnresta);
        btnresta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float num1 = Float.parseFloat(numero1.getText().toString());
                float num2 = Float.parseFloat(numero2.getText().toString());
                respuestaOperacion = num1 - num2;
            }
        });

        Button btnmultiplicar = findViewById(R.id.btnmultiplicar);
        btnmultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float num1 = Float.parseFloat(numero1.getText().toString());
                float num2 = Float.parseFloat(numero2.getText().toString());
                respuestaOperacion = num1 * num2;
            }
        });

        Button btndividir = findViewById(R.id.btndividir);
        btndividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float num1 = Float.parseFloat(numero1.getText().toString());
                float num2 = Float.parseFloat(numero2.getText().toString());
                respuestaOperacion = num1 / num2;
            }
        });

        Button btniguala = findViewById(R.id.btniguala);
        btniguala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText(" " +respuestaOperacion);
            }
        });
    }

}
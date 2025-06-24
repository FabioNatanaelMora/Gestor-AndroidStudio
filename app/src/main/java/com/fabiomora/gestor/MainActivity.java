package com.fabiomora.gestor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Paso 1: Declaro mi variable con el tipo de control
    Button btnWeb,btnIngresar;
    TextView tvRegistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Paso 2: Asociar mi variable creada al control de mi xml activity
        btnWeb = findViewById(R.id.btnWeb);
        btnIngresar= findViewById(R.id.btnIngresar);
        tvRegistrate = findViewById(R.id.tvRegistrate);

        //Paso 3: Le voy a indicar a la variable que es lo que vas a hacer


        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast es el mensajito tipo globito,
                //Toast.makeText(MainActivity.this,"Buenas noches soy el boton!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, PaginaWeb.class));
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Ingresaste!",Toast.LENGTH_SHORT).show();

            }
        });

        tvRegistrate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity((new Intent(MainActivity.this, Registro.class)));
            }
        });


    }
}
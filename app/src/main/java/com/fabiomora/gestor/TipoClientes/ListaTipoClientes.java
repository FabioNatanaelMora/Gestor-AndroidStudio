package com.fabiomora.gestor.TipoClientes;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fabiomora.gestor.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaTipoClientes extends AppCompatActivity {

    ImageView ivBack;
    FloatingActionButton btnAgregarTipoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tipo_clientes);

        ivBack = findViewById(R.id.ivBack);
        btnAgregarTipoCliente = findViewById(R.id.btnAgregarTipoClientes);

        ivBack.setOnClickListener(v -> finish());

        btnAgregarTipoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListaTipoClientes.this,"Modulo Agregar Tipo Cliente", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
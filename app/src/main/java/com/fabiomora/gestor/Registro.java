package com.fabiomora.gestor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {

    EditText etNombres, etApellidos, etCorreo, etClave;
    Button btnRegistrarR;
    TextView tvIrLogin;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String nombres="", apellidos="", correo= "" , contrasena = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombres = findViewById(R.id.etNombresR);
        etApellidos = findViewById(R.id.etApellidosR);
        etCorreo = findViewById(R.id.etCorreoR);
        etClave = findViewById(R.id.etContrasenaR);
        btnRegistrarR = findViewById(R.id.btnRegistrarR);
        tvIrLogin = findViewById(R.id.tvIrLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(Registro.this);
        progressDialog.setTitle("Espero por favor.....");
        progressDialog.setCanceledOnTouchOutside(false);

        btnRegistrarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Registro.this, "Te Registraste!", Toast.LENGTH_SHORT).show();
                validarDatos();
            }
        });

        tvIrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void validarDatos() {
        nombres = etNombres.getText().toString().trim();
        apellidos = etApellidos.getText().toString().trim();
        correo = etCorreo.getText().toString().trim();
        contrasena = etClave.getText().toString().trim();

        if(TextUtils.isEmpty(nombres)){
            Toast.makeText(this, " El campo nombre esta vacio", Toast.LENGTH_SHORT).show();
        } else if (!TextUtils.isEmpty(nombres)) {
            Toast.makeText(this, " Tiene un nombre", Toast.LENGTH_SHORT).show();

        }

    }

}
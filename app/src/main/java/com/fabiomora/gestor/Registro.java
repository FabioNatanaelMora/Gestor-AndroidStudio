package com.fabiomora.gestor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

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
        } else if (TextUtils.isEmpty(apellidos)) {
            Toast.makeText(this, "El campo Apellido esta vacio", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Ingrese corro valido", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(contrasena)|| contrasena.length()<6){
            Toast.makeText(this, "Ingrese contraseña minimo 6 caracteres",Toast.LENGTH_SHORT).show();
        }else {
            //Toast.makeText(this, "Registrar",Toast.LENGTH_SHORT).show();
            registrar();
        }


    }

    private void registrar() {
        progressDialog.setMessage("Registrando Usuario...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(correo, contrasena)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        guardarUsuario();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Registro.this, "Ocurrio un problema, revisa los campos", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void guardarUsuario() {
        progressDialog.setMessage("Guardando usuario...");
        progressDialog.show();

        String uid= firebaseAuth.getUid();
        HashMap<String,String> datosUsuario = new HashMap<>();
        datosUsuario.put("uid", uid);
        datosUsuario.put("nombres_usuarios", nombres);
        datosUsuario.put("apellidos",apellidos);
        datosUsuario.put("correo",correo);
        datosUsuario.put("contrasena", contrasena);
        datosUsuario.put("estado","1");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios");
        assert uid != null;
        databaseReference.child(uid).setValue(datosUsuario).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(Registro.this,"Usuario Registro Correctamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Registro.this, Dashboard.class));
                finish();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText( Registro.this, "Ocurrio un problema al guardar los datos",Toast.LENGTH_SHORT).show();

            }
        });

    }


}
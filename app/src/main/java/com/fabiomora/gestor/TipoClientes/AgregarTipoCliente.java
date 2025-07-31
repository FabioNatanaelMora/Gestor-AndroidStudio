package com.fabiomora.gestor.TipoClientes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fabiomora.gestor.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AgregarTipoCliente extends AppCompatActivity {

    ImageView ivBack;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText etTipoCliente;
    Button btnRegistrarTipo;
    String id, nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tipo_cliente);

        ivBack = findViewById(R.id.ivBack);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("TipoClientes");

        id = databaseReference.push().getKey();

        etTipoCliente = findViewById(R.id.etTipoCliente);
        btnRegistrarTipo = findViewById(R.id.btnRegistrarTipo);

        btnRegistrarTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = etTipoCliente.getText().toString().trim();

                if(!nombre.isEmpty()){
                    //Toast.makeText(AgregarTipoCliente.this,"hay un dato",Toast.LENGTH_SHORT).show();
                    agregarTipoCliente();
                }else{
                    Toast.makeText(AgregarTipoCliente.this, "Ingrese Tipo Cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void agregarTipoCliente() {

        Map<String,String> data = new HashMap<>();
        data.put("id",id);
        data.put("nombre",nombre);
        /*
        databaseReference.child(id).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(AgregarTipoCliente.this, "Registro Realizado Correctamente", Toast.LENGTH_SHORT).show();
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AgregarTipoCliente.this, "Ocurrio un problema", Toast.LENGTH_SHORT).show();

            }
        });*/

        databaseReference.child(firebaseUser.getUid()).child(id).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(AgregarTipoCliente.this, "Registro Realizado Correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AgregarTipoCliente.this, "Ocurrio un problema", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //Paso 1: Declaro mi variable con el tipo de control
    EditText etCorreo, etcontasenia;
    Button btnWeb,btnIngresar;
    TextView tvRegistrate;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String correo="",contrasenia="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Paso 2: Asociar mi variable creada al control de mi xml activity
        etCorreo = findViewById(R.id.etUsuario); //ResultSet = R
        etcontasenia= findViewById(R.id.etClave);
        btnWeb = findViewById(R.id.btnWeb);
        btnIngresar= findViewById(R.id.btnIngresar);
        tvRegistrate = findViewById(R.id.tvRegistrate);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Aviso de Aplicativo");
        progressDialog.setCanceledOnTouchOutside(false); //Para que no lo puedan cancelar

        etCorreo.setText("fabio@gmail.com");
        etcontasenia.setText("123456");

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
                validadDatos();

            }
        });

        tvRegistrate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity((new Intent(MainActivity.this, Registro.class)));
            }
        });
    }
    private void validadDatos() {

        correo = etCorreo.getText().toString().trim();
        contrasenia = etcontasenia.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            Toast.makeText(MainActivity.this,"Ingrese correo valido", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(contrasenia)){
            Toast.makeText(this,"Ingrese Contraseña", Toast.LENGTH_SHORT).show();
        }else {
            //Toast.makeText(this,"Verificando datos", Toast.LENGTH_SHORT).show();
            logearUsuario();
        }
    }

    private void logearUsuario() {
        progressDialog.setMessage("Iniciando Sesion...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(correo,contrasenia)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this, Dashboard.class));
                            Toast.makeText(MainActivity.this, "Bienvenido a FabioMax", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Verifique si el correo o contraseña son correctos", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}
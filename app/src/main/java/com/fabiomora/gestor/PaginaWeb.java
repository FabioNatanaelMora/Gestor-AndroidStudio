package com.fabiomora.gestor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaginaWeb extends AppCompatActivity {

    WebView wvPagina;
    WebSettings webSettings;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_web);

        //Asocia o inicializa la variable
        wvPagina = findViewById(R.id.wvPagina);
        webSettings = wvPagina.getSettings();
        //Una web usa el Dom y JavaScript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        wvPagina.loadUrl("https://aymperusi.com/");
        //Para que no se salga de la aplicacion
        wvPagina.setWebChromeClient(new WebChromeClient());

    }

    @Override
    public void onBackPressed() {
        if(wvPagina.canGoBack()){
            wvPagina.canGoBack();
        }else {
        super.onBackPressed();
    }}
}
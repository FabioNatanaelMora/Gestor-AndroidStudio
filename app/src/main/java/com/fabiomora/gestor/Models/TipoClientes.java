package com.fabiomora.gestor.Models;

public class TipoClientes {
    String id;
    String nombre;

    public TipoClientes() {
    }

    public TipoClientes(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

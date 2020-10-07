package com.example.recyclerview.model;

import java.io.Serializable;

public class ItemList implements Serializable {
    private String titulo;
    private String descripcion;
    private String imgResource;

    public ItemList(String titulo, String descripcion, String imgResource) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imgResource = imgResource;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImgResource() {
        return imgResource;
    }
}

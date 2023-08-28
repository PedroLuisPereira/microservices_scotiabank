package com.example.userservice.model;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private Long id;

    private String nombre;

    public Role() {
    }

    public Role(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", nombre=" + nombre + "]";
    }

}

package com.example.authserver.dto;


public class Role {

    private Long id;

    private String nombre;

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

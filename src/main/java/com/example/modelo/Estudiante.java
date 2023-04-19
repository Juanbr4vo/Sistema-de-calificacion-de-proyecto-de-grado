package com.example.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ESTUDIANTES")
public class Estudiante {

    @Id
    @Column(name = "cedula", updatable = false)
    private long id;
    @Column(nullable = true)
    private String tipo_doc;
    @Column(nullable = true)
    private String nombre;
    @Column(nullable = true)
    private String apelllido;
    @Column(nullable = true, unique = true)
    private String email;
    @Column(nullable = true, unique = true)
    private String telefono;
    @Column(nullable = true)
    private long semestre;
    private long id_carrera;

    // Vacio
    public Estudiante() {
    }

    // Constructor
    public Estudiante(long id, String tipo_doc, String nombre, String apelllido, String email,
            String telefono, long semestre, long id_carrera) {
        this.id = id;
        this.tipo_doc = tipo_doc;
        this.nombre = nombre;
        this.apelllido = apelllido;
        this.email = email;
        this.telefono = telefono;
        this.semestre = semestre;
        this.id_carrera = id_carrera;
    }

    // get and set
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApelllido() {
        return apelllido;
    }

    public void setApelllido(String apelllido) {
        this.apelllido = apelllido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long getSemestre() {
        return semestre;
    }

    public void setSemestre(long semestre) {
        this.semestre = semestre;
    }

    public long getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(long id_carrera) {
        this.id_carrera = id_carrera;
    }

}

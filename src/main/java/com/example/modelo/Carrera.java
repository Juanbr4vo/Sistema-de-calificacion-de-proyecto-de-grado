package com.example.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARRERAS")
public class Carrera {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	private long id;
	@Column(unique = true)
	private String nombre;
	private long id_cordinador;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getId_cordinador() {
		return id_cordinador;
	}

	public void setId_cordinador(long id_cordinador) {
		this.id_cordinador = id_cordinador;
	}

	public Carrera() {
	}


	public Carrera(long id, String nombre, long id_cordinador) {
		this.id = id;
		this.nombre = nombre;
		this.id_cordinador = id_cordinador;
	}	
	
}

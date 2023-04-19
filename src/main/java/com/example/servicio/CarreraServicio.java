package com.example.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.modelo.Carrera;


@Service
public interface CarreraServicio {

	public List<Carrera> listar();
	
	public Carrera encontrarPorId(long id);
	
	public void eliminar(Carrera carr);

    public long contarRegistro();
    
    public Carrera guardar(Carrera carr);
	
}

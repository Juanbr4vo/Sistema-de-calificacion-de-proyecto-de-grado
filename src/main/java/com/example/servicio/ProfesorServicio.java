package com.example.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.modelo.Profesor;
@Service
public interface ProfesorServicio {
	
	public List<Profesor> findAll(); 
	
	public Profesor guardar(Profesor pro);	
	
	public Profesor encontrarPorId(Long id);
	
	public void eliminar(Profesor pro);
	
	public long contarRegistro();
	

	
	
}

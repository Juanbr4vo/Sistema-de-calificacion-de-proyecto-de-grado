package com.example.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.modelo.Estudiante;

@Service
public interface EstudianteServicio {
    
    public List<Estudiante> findAll();

    public Estudiante guardar(Estudiante estudiante);

    public Estudiante encontrarPorId(Long id);

    public void eliminar(Estudiante estudiante);

    public long contarRegistro();
}

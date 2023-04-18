package com.example.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.estudianteDao;
import com.example.modelo.Estudiante;

import jakarta.transaction.Transactional;

@Service
public class EstudianteServicioImp implements EstudianteServicio{

    @Autowired
    private estudianteDao estudianteDao;

    @Override
    @Transactional
    public long contarRegistro() {
        return estudianteDao.count();
    }

    @Override
    @Transactional
    public void eliminar(Estudiante estudiante) {
        estudianteDao.delete(estudiante);
    }

    @Override
    public Estudiante encontrarPorId(Long id) {
        return estudianteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<Estudiante> findAll() {
        return (List<Estudiante>) estudianteDao.findAll();
    }

    @Override
    @Transactional
    public Estudiante guardar(Estudiante estudiante) {
        return estudianteDao.save(estudiante);
    }

    @Override
    public String toString() {
        return "EstudianteServicioImp []";
    }
    
}

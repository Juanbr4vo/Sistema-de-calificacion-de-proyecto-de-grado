package com.example.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.modelo.Coordinador;

@Service
public interface CoordinadorServicio {

    public List<Coordinador> findAll();

    public Coordinador guardar(Coordinador cor);

    public Coordinador encontrarPorId(Long cor);

    public void eliminar(Coordinador cor);

    public long contarRegistro();
}

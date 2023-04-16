package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modelo.Estudiante;

@Repository
public interface estudianteDao extends JpaRepository<Estudiante,Long> {
    
}

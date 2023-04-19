package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modelo.Carrera;

@Repository
public interface CarreraDao extends JpaRepository<Carrera, Long> {

}

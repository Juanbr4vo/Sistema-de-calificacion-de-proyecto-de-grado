package com.example.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modelo.Coordinador;

@Repository
public interface coordinadorDao extends JpaRepository<Coordinador,Long>{
}
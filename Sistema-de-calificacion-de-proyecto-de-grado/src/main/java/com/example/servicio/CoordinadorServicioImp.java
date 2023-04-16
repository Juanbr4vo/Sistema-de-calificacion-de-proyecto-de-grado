package com.example.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.coordinadorDao;
import com.example.modelo.Coordinador;

import jakarta.transaction.Transactional;
@Service
public class CoordinadorServicioImp implements CoordinadorServicio{

	
	@Autowired
	private coordinadorDao corDao;
	


	@Override
	@Transactional
	public Coordinador guardar(Coordinador cor) {
		return corDao.save(cor);
	}

	@Transactional
	public Coordinador encontrarPorId(Long id) {
		return corDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void eliminar(Coordinador cor) {
		corDao.delete(cor);
		
	}

	@Override
	@Transactional
	public long contarRegistro() {
		return corDao.count();
	}

	@Override
	@Transactional
	public List<Coordinador> findAll() {
		return (List<Coordinador>) corDao.findAll();
	}

	
	

	
	

	

	
	

}
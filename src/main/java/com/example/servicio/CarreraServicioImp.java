package com.example.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CarreraDao;
import com.example.modelo.Carrera;

import jakarta.transaction.Transactional;


@Service
public class CarreraServicioImp implements CarreraServicio{

	@Autowired
	private CarreraDao carDao;
	
	@Override
	@Transactional
	public List<Carrera> listar() {
		return (List<Carrera>) carDao.findAll();
	}

	@Override
	@Transactional
	public Carrera encontrarPorId(long id) {
		return carDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void eliminar(Carrera carr) {
		carDao.delete(carr);
		
	}

	@Override
	@Transactional
	public long contarRegistro() {
		return carDao.count();
	}

	@Override
	@Transactional
	public Carrera guardar(Carrera carr) {
		return carDao.save(carr);
	}

}

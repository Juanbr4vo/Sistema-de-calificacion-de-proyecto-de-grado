package com.example.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.profesorDao;
import com.example.modelo.Profesor;

import jakarta.transaction.Transactional;
@Service
public class ProfesorServicioImp implements ProfesorServicio{

	
	@Autowired
	private profesorDao proDao;
	


	@Override
	@Transactional
	public Profesor guardar(Profesor pro) {
		return proDao.save(pro);
	}

	@Transactional
	public Profesor encontrarPorId(Long id) {
		return proDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void eliminar(Profesor pro) {
		proDao.delete(pro);
		
	}

	@Override
	@Transactional
	public long contarRegistro() {
		return proDao.count();
	}

	@Override
	@Transactional
	public List<Profesor> findAll() {
		return (List<Profesor>) proDao.findAll();
	}

	
	

	
	

	

	
	

}

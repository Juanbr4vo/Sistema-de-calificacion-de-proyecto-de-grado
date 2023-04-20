package com.example.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.modelo.Carrera;
import com.example.servicio.CarreraServicio;

@RestController
@RequestMapping("/api/v1/")
public class CarreraControlador {

	@Autowired
	private CarreraServicio carSer;
	
	//#####LISTAR CARRERAS########
	@GetMapping(value ="carrera")
	public ResponseEntity<Object> get(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (carSer.contarRegistro()==0) {
			map.put("Mensaje:  ", "No existe ningun registro");
			return new ResponseEntity<>(map , HttpStatus.OK);
		} else {
			try {
				List<Carrera> list = carSer.listar();
				return new ResponseEntity<Object>(list,HttpStatus.OK);
			} catch (Exception e) {
				map.put("Mensaje de error L: ", e.getMessage());
				return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}	
	}
	
	//######### CANTIDAD DE REGISTROS ###########
	@GetMapping(value = "carrera/cantidad")
	public ResponseEntity<Object> contar(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long cantidad = carSer.contarRegistro();
			map.put("Cantidad de resgistros de carrera: ", cantidad);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		} catch (Exception e) {
			map.put("Mensaje de error Co: ", e.getMessage());
			return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(value = "carrera")
	public ResponseEntity<Object> crear(@RequestBody Carrera car){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (carSer.encontrarPorId(car.getId())== null) {
			try {
				Carrera res = carSer.guardar(car);
				return new ResponseEntity<Object>(res,HttpStatus.OK);
			} catch (Exception e) {
				map.put("Mensaje de error C: ", e.getMessage()+" Podria ser que el nombre ya este registrado");
				return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			map.put("Mensaje de error:", "La carrea con id "+car.getId()+" ya se encuentra registado");
			return new ResponseEntity<>(map , HttpStatus.OK);
		}
			
	}
	
	
	@PutMapping(value = "carrera/{id}")
	public ResponseEntity<Object> actualizar(@RequestBody Carrera car, @PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		if (carSer.encontrarPorId(id)!= null) {
			try {
				Carrera currentCarrera = carSer.encontrarPorId(id);
				//currentCarrera.setId(id);
				currentCarrera.setNombre(car.getNombre());
				currentCarrera.setId_cordinador(car.getId_cordinador());
				Carrera res = carSer.guardar(currentCarrera);
				
				return new ResponseEntity<Object>(res,HttpStatus.OK);
			} catch (Exception e) {
				map.put("Mensaje de error A: ", e.getMessage());
				return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			map.put("Mensaje A: ", "La carrera con id "+id+" no se encuentra registrada");
			return new ResponseEntity<>(map , HttpStatus.OK);
		}	
	}
	
	@GetMapping(value = "carrera/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Carrera data = carSer.encontrarPorId(id);
		
		if (data!=null) {
			try {
				return new ResponseEntity<Object>(data,HttpStatus.OK);
			} catch (Exception e) {
				map.put("Mensaje de error BPI: ", e.getMessage());
				return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			map.put("Mensaje de error BPI: ", "No se encuentra registro con id: "+id);
			return new ResponseEntity<>(map , HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping(value = "carrera/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Carrera currentCarrera = carSer.encontrarPorId(id);
		if (currentCarrera!=null) {
			try {
				
				carSer.eliminar(currentCarrera);
				map.put("Deleted: ", true);
				return new ResponseEntity<Object>(map,HttpStatus.OK);
			} catch (Exception e) {
				map.put("Mensaje de error E: ", e.getMessage());
				return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			map.put("Mensaje de error E: ", "No se encuentra registro con id: "+id);
			return new ResponseEntity<>(map , HttpStatus.OK);
		}
			
	}
}

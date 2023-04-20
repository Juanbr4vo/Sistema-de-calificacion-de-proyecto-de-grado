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
import com.example.modelo.Coordinador;
import com.example.servicio.CoordinadorServicio;


@RestController
@RequestMapping("/api/v1/")
public class CoordinadorControlador {
	
	@Autowired
	private CoordinadorServicio coordinadorServicio;
	
	@GetMapping(value = "coordinador")
	public ResponseEntity<Object> get(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (coordinadorServicio.contarRegistro()==0) {
			map.put("Mensaje:  ", "No existe ningun registro");
			return new ResponseEntity<>(map , HttpStatus.OK);
		} else {
			try {
				List<Coordinador> list = coordinadorServicio.findAll();
				return new ResponseEntity<Object>(list,HttpStatus.OK);
			} catch (Exception e) {
				map.put("Mensaje de error L: ", e.getMessage());
				return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}	
	}
	
	@GetMapping(value = "coordinador/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Coordinador data = coordinadorServicio.encontrarPorId(id);
		
		if (data!=null) {
			try {
				return new ResponseEntity<Object>(data,HttpStatus.OK);
			} catch (Exception e) {
				map.put("Mensaje de error BPI: ", e.getMessage());
				return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			map.put("Mensaje de error BPI: ", "No se encuentra ningun coordinador con numero de cedula: "+id);
			return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			
	}
	
	@PostMapping(value = "coordinador")
	public ResponseEntity<Object> create(@RequestBody Coordinador cor){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (coordinadorServicio.encontrarPorId(cor.getId())== null) {
			try {
				Coordinador res = coordinadorServicio.guardar(cor);
				return new ResponseEntity<Object>(res,HttpStatus.OK);
			} catch (Exception e) {
				map.put("Mensaje de error C: ", e.getMessage()+" Podria ser que el correo y/o numero de telefono ya esten registrados");
				return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			map.put("Mensaje de error:", "El coordinador con cedula "+cor.getId()+" ya se encuentra registado");
			return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	
	@PutMapping(value = "coordinador/{id}")
	public ResponseEntity<Object> actualizar(@RequestBody Coordinador coordinador, @PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();

		Coordinador currentCoordinador = coordinadorServicio.encontrarPorId(id);
		if (currentCoordinador!=null) {
			
			try {
		
				currentCoordinador.setNombre(coordinador.getNombre());
				currentCoordinador.setApellido(coordinador.getApellido());
				currentCoordinador.setEmail(coordinador.getEmail());
				currentCoordinador.setTelefono(coordinador.getTelefono());
				
				Coordinador res = coordinadorServicio.guardar(currentCoordinador);
				
				return new ResponseEntity<Object>(res,HttpStatus.OK);
			} catch (Exception e) {
				map.put("Mensaje de error A: ", e.getMessage());
				return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			map.put("Mensaje A: ", "No se ha encontrado coordinador con cedula: "+id);
			return new ResponseEntity<>(map , HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "coordinador/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Coordinador currentCoordinador = coordinadorServicio.encontrarPorId(id);
		if (currentCoordinador!=null) {
			try {
				
				coordinadorServicio.eliminar(currentCoordinador);
				map.put("Deleted: ", true);
				return new ResponseEntity<Object>(map,HttpStatus.OK);
			} catch (Exception e) {
				map.put("Mensaje de error E: ", e.getMessage());
				return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			map.put("Mensaje de error E: ", "El numero de cedula "+id+" no se encuentra registrado");
			return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@GetMapping(value = "coordinador/cantidad")
	public ResponseEntity<Object> contar(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long cantidad = coordinadorServicio.contarRegistro();
			map.put("Cantidad de resgistros de coordinadores: ", cantidad);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		} catch (Exception e) {
			map.put("Mensaje de error Co: ", e.getMessage());
			return new ResponseEntity<>(map , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
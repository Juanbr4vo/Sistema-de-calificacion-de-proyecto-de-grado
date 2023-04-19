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

import com.example.modelo.Estudiante;
import com.example.servicio.EstudianteServicio;

@RestController
@RequestMapping("/api/v1")
public class EstudianteControlador {
    
    @Autowired
    private EstudianteServicio estudianteServicio;

    @GetMapping(value = "estudiante")
    public ResponseEntity<Object> get() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (estudianteServicio.contarRegistro()==0) {
            map.put("Mensaje: ", "No existe ningún registro actualmente");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            try {
                List<Estudiante> list = estudianteServicio.findAll();
                return new ResponseEntity<Object>(list,HttpStatus.OK);
            } catch (Exception e) {
                map.put("Mensaje error Listar: ", e.getMessage());
                return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping(value = "estudiante/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Estudiante data = estudianteServicio.encontrarPorId(id);

        if (data != null) {
            try {
                return new ResponseEntity<Object>(data, HttpStatus.OK);
            } catch (Exception e) {
                map.put("Mensaje error BPI: ", e.getMessage());
                return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            map.put("Mensaje error BPI: ", "No se econtrató ningún registro de estudiante con cedula: "+id);
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "estudiante")
    public ResponseEntity<Object> create(@RequestBody Estudiante estudiante) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (estudianteServicio.encontrarPorId(estudiante.getId()) == null) {
            try {
                Estudiante result = estudianteServicio.guardar(estudiante);
                return new ResponseEntity<Object>(result, HttpStatus.OK);
            } catch (Exception e) {
                map.put("Mensaje error Crear: ", e.getMessage()+"Numero de telefono o correo ya se encuentran registrados");
                return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            map.put("Mensaje error: ", "Estudiante con cedula: "+estudiante.getId()+" ya se encuentra en el registro");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @PutMapping(value = "estudiante/{id}")
    public ResponseEntity<Object> actualizar(@RequestBody Estudiante estudiante, @PathVariable Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Estudiante currentEstudiante = estudianteServicio.encontrarPorId(id);
        if (currentEstudiante != null) {
            try {
                // currentEstudiante.setId(id);
                currentEstudiante.setTipo_doc(estudiante.getTipo_doc());
                currentEstudiante.setNombre(estudiante.getNombre());
                currentEstudiante.setApelllido(estudiante.getApelllido());
                currentEstudiante.setEmail(estudiante.getEmail());
                currentEstudiante.setTelefono(estudiante.getTelefono());
                currentEstudiante.setSemestre(estudiante.getSemestre());
                currentEstudiante.setId_carrera(estudiante.getId_carrera());
                Estudiante result = estudianteServicio.guardar(currentEstudiante);
    
                return new ResponseEntity<Object>(result, HttpStatus.OK);
    
            } catch (Exception e) {
                map.put("Mensaje error Actualizar: ", e.getMessage());
                return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            map.put("Mensaje Actualizar: ", "El numero de cedula "+id+" no se encuentra en el registro");
			return new ResponseEntity<>(map , HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "estudiante/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Estudiante currentEstudiante = estudianteServicio.encontrarPorId(id);
        if (currentEstudiante != null) {
            try {
                estudianteServicio.eliminar(currentEstudiante);
                map.put("Deleted: ", true);
                return new ResponseEntity<Object>(map, HttpStatus.OK);
            } catch (Exception e) {
                map.put("Mensaje error Eliminar: ", e.getMessage());
                return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            map.put("Mensaje error Eliminar: ", "El numero de cedula "+id+" no se encuentra en el registro");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "estudiante/cantidad")
    public ResponseEntity<Object> contar() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long cantLong = estudianteServicio.contarRegistro();
            map.put("Cantidad de registro -> Estudiantes: ", cantLong);
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put("Mensaje error CantReg: ", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

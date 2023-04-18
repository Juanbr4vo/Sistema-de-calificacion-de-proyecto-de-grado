package com.example.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        } else {
            try {
                List<Estudiante> list = estudianteServicio.findAll();
                return new ResponseEntity<Object>(list,HttpStatus.OK);
            } catch (Exception e) {
                map.put("Mensaje error L: ", e.getMessage());
                return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}

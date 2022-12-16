package com.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Persona;
import com.crud.services.IPersonaService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class PersonaController {
	@Autowired
	IPersonaService personaService;
	
	@GetMapping("/personas")
	public List<Persona> findAllPersonas() {
		return personaService.findAll();
	}
	
	@GetMapping("/personas/{nombre}")
	public  List<Persona> findByNombre(@PathVariable String nombre) {
		return personaService.findByNombre(nombre);	
	}
	
	@DeleteMapping("/personas/{id}")
	public ResponseEntity<?> deletePersona(@PathVariable String identificacion) {

		Map<String, Object> response = new HashMap<>();

		try {
			personaService.deletePersona(identificacion);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la persona de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La persona eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/personas/{identificacion}")
	public ResponseEntity<?> update(@RequestBody Persona persona, BindingResult result, @PathVariable String identificacion) {
		
		Persona personaActual = personaService.findByIdentificacion(identificacion);
		
		Persona personaActualizada = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if (personaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la Persona con ID: "
					.concat(identificacion.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {

			personaActual.setIdentificacion(persona.getIdentificacion());
			personaActual.setNombre(persona.getNombre());
			personaActual.setFechaCreacion(persona.getFechaCreacion());

			personaActualizada = personaService.savePersona(personaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		response.put("mensaje", "La persona ha sido actualizado con éxito!");
		response.put("usuario", personaActualizada);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
}

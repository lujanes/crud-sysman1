package com.crud.services;

import java.util.List;

import com.crud.entity.Persona;

public interface IPersonaService {
		
	public List<Persona> findAll();
	
	public List<Persona> findByNombre(String nombre);
	
	public Persona findById(Long id);
	
	public Persona findByIdentificacion(String identificacion);
	
	public Persona savePersona(Persona persona);
	
	public void deletePersona(String identificacion); 

}

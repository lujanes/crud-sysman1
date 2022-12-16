package com.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.dao.IPersonaDao;
import com.crud.entity.Persona;

@Service
public class PersonaService implements IPersonaService {
	
	@Autowired
	IPersonaDao personaDAO;

	@Override
	@Transactional(readOnly=true)
	public List<Persona> findAll() {		
		return personaDAO.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Persona> findByNombre(String nombre) {		
		return personaDAO.findByNombre(nombre);
	}

	@Override
	@Transactional(readOnly=true)
	public Persona findByIdentificacion(String identificacion) {
		return personaDAO.findByIdentificacion(identificacion);
	}

	@Override
	@Transactional
	public Persona savePersona(Persona persona) {
		return personaDAO.save(persona);
	}

	@Override
	@Transactional
	public void deletePersona(String identificacon) {
		personaDAO.deleteByIdentificacion(identificacon);
	}

	@Override
	public Persona findById(Long id) {		
		return personaDAO.findById(id).orElse(null);
	}
	
	
	
}

package com.crud.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.entity.Persona;

@Repository
public interface IPersonaDao extends CrudRepository<Persona, Long> {
	
	public List<Persona> findAll();
	
	public List<Persona> findByNombre(String nombre);
	
	public Persona findByIdentificacion(String identificacion);

	public void deleteByIdentificacion(String identificacon);

}

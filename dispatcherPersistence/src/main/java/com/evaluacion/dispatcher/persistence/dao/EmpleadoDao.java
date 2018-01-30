package com.evaluacion.dispatcher.persistence.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.evaluacion.dispatcher.persistence.entities.Cargo;
import com.evaluacion.dispatcher.persistence.entities.Empleado;

public interface EmpleadoDao extends CrudRepository<Empleado, Integer>{
	public List<Empleado>  findByIdCargoAndOcupado(Cargo idcargo,Boolean ocupado);
	

}

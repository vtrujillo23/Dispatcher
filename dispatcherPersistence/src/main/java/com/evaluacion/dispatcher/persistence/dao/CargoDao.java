package com.evaluacion.dispatcher.persistence.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.evaluacion.dispatcher.persistence.entities.Cargo;

public interface CargoDao extends CrudRepository<Cargo, Integer>{
	public List<Cargo> findByOrderByPrioridadAsc();

}

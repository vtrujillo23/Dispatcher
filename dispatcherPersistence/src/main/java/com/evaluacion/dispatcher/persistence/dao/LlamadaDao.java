package com.evaluacion.dispatcher.persistence.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.evaluacion.dispatcher.persistence.entities.Llamada;

public interface LlamadaDao extends CrudRepository<Llamada,Integer>{
	public List<Llamada> findTop10ByWaitingOrderByDateDesc(Boolean waiting);
	

}

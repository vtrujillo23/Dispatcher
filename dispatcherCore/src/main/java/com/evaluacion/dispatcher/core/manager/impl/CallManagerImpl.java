package com.evaluacion.dispatcher.core.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.dispatcher.core.dto.CallDto;
import com.evaluacion.dispatcher.core.dto.ResponseDto;
import com.evaluacion.dispatcher.core.exception.DispatcherException;
import com.evaluacion.dispatcher.core.manager.CallManager;
import com.evaluacion.dispatcher.persistence.dao.CargoDao;
import com.evaluacion.dispatcher.persistence.dao.EmpleadoDao;
import com.evaluacion.dispatcher.persistence.dao.LlamadaDao;
import com.evaluacion.dispatcher.persistence.entities.Cargo;
import com.evaluacion.dispatcher.persistence.entities.Empleado;
import com.evaluacion.dispatcher.persistence.entities.Llamada;


@Service
public class CallManagerImpl implements CallManager{
	@Autowired
	private LlamadaDao llamadaDao;
	@Autowired
	private CargoDao cargoDao;
	@Autowired
	private EmpleadoDao empleadoDao;

	@Override
	public String manageCall(CallDto calldto) throws DispatcherException{
		try {
		Llamada llamada=new Llamada();
		llamada.setIdCall(calldto.getId_call());
		llamada.setFromcall(calldto.getFrom());
		llamada.setTocall(calldto.getTo());
		llamada.setWaiting(true);
		llamada.setDate(new Date());
		llamadaDao.save(llamada);
		ResponseDto responseDto=new ResponseDto();
		responseDto.setIdcall(llamada.getId());
		Empleado empleado=getEmpleadoDisponible();
		asiganteCall(llamada,empleado);
		return llamada.getId()+"";
		}catch(Exception e) {
			throw new  DispatcherException("error con la aplicacion",e);
		}
	}
	
	public ResponseDto freeCall(Integer idCall) throws DispatcherException{
		try {
		Llamada llamada=llamadaDao.findOne(idCall);
		if(llamada!=null) {
		llamada.setWaiting(false);
		llamadaDao.save(llamada);
		Empleado empleado=empleadoDao.findOne(llamada.getIdEmpleado().getId());
		empleado.setOcupado(false);
		empleadoDao.save(empleado);
		ResponseDto responseDto=new ResponseDto();
		responseDto.setIdcall(llamada.getId());
		return responseDto;
		}else {
			throw new  DispatcherException("La llamada no existe");
		}
		}catch(DispatcherException ex) {
			throw ex;
		}catch(Exception e) {
			throw new  DispatcherException("error con la aplicacion",e);
		}
	}
	
	public Empleado getEmpleadoDisponible() {
		Empleado empleado=null;
		List<Cargo> cargos=cargoDao.findByOrderByPrioridadAsc();
		for(Cargo cargo:cargos) {
			List<Empleado> empleadosDisponibles=empleadoDao.findByIdCargoAndOcupado(cargo, Boolean.FALSE);
			if(!empleadosDisponibles.isEmpty()) {
				empleado=empleadosDisponibles.get(0);
				break;
			}
		}
		return empleado;
	}
	
	public void asiganteCall(Llamada llamada,Empleado empleado) {
		if(empleado!=null) {
			llamada.setIdEmpleado(empleado);
			llamada.setWaiting(false);
			llamadaDao.save(llamada);
			empleado.setOcupado(Boolean.TRUE);
			empleadoDao.save(empleado);
		}
	}
	

}

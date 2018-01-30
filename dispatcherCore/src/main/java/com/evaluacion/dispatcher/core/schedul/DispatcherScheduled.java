package com.evaluacion.dispatcher.core.schedul;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.evaluacion.dispatcher.core.manager.CallManager;
import com.evaluacion.dispatcher.persistence.dao.LlamadaDao;
import com.evaluacion.dispatcher.persistence.entities.Empleado;
import com.evaluacion.dispatcher.persistence.entities.Llamada;

@Component
public class DispatcherScheduled {
	@Autowired
	private LlamadaDao llamadaDao;
	@Autowired
	private CallManager callManager;
	
	
	@Scheduled(fixedRate = 15000)
	public void disptachWaitingCalls() {
		List<Llamada> llamadas=llamadaDao.findTop10ByWaitingOrderByDateDesc(Boolean.TRUE);
		for(Llamada llamada:llamadas) {
			Empleado empleado= callManager.getEmpleadoDisponible();
			if(empleado!=null) {
				callManager.asiganteCall(llamada, empleado);
			}
		}
	}

}

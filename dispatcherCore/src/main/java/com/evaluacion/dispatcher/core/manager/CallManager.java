package com.evaluacion.dispatcher.core.manager;

import com.evaluacion.dispatcher.core.dto.CallDto;
import com.evaluacion.dispatcher.core.dto.ResponseDto;
import com.evaluacion.dispatcher.core.exception.DispatcherException;
import com.evaluacion.dispatcher.persistence.entities.Empleado;
import com.evaluacion.dispatcher.persistence.entities.Llamada;

public interface CallManager {
	public String manageCall(CallDto calldto) throws DispatcherException;
	public ResponseDto freeCall(Integer idCall) throws DispatcherException;
	public Empleado getEmpleadoDisponible();
	public void asiganteCall(Llamada llamada,Empleado empleado);
}

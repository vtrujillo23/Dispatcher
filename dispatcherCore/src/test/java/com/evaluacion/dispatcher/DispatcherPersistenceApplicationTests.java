package com.evaluacion.dispatcher;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.evaluacion.dispatcher.core.conf.DispacherCoreConfiguration;
import com.evaluacion.dispatcher.core.dto.CallDto;
import com.evaluacion.dispatcher.core.manager.CallManager;
import com.evaluacion.dispatcher.persistence.configuration.DispatcherPersistenceConf;
import com.evaluacion.dispatcher.persistence.dao.CargoDao;
import com.evaluacion.dispatcher.persistence.dao.EmpleadoDao;
import com.evaluacion.dispatcher.persistence.dao.LlamadaDao;
import com.evaluacion.dispatcher.persistence.entities.Cargo;
import com.evaluacion.dispatcher.persistence.entities.Empleado;
import com.evaluacion.dispatcher.persistence.entities.Llamada;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DispacherCoreConfiguration.class)
public class DispatcherPersistenceApplicationTests {
	@Autowired
	CargoDao cargoDao;
	@Test
	public void  testDaos() {
		try {
			List<Cargo> cargos=cargoDao.findByOrderByPrioridadAsc();
			System.out.println("salida "+cargos.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

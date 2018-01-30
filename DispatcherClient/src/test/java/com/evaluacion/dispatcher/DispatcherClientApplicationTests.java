package com.evaluacion.dispatcher;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.evaluacion.dispatcher.client.DispatcherClient;
import com.evaluacion.dispatcher.client.configuration.DispatcherClientConfiguration;
import com.evaluacion.dispatcher.client.dto.CallDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DispatcherClientConfiguration.class)
public class DispatcherClientApplicationTests {
	@Autowired
	private DispatcherClient dispatcherClient;
	
	@Test
	public void testClienteDispatch() {
		try {
		CallDto callDto=new CallDto();
		callDto.setId_call("T1200");
		callDto.setFrom("3456789");
		callDto.setTo("3456789");
		dispatcherClient.dispatch(callDto, "http://localhost:8079/dispatch");
		System.out.println("salidaaa ");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void testfreeCall() {
		try {
			dispatcherClient.freeCall("31", "http://localhost:8079/freeCall");
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void randomGeneratingCall() {
		List<ThreadCreateCall> threads=new ArrayList<ThreadCreateCall>();
		for(int i=0;i<20;i++) {
			threads.add(new ThreadCreateCall(dispatcherClient,"http://localhost:8079/"));
		}
		for(int i=0;i<threads.size();i++) {
			threads.get(i).run();
		}
	}

}

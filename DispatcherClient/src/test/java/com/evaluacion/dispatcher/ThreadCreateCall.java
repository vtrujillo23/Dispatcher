package com.evaluacion.dispatcher;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.evaluacion.dispatcher.client.DispatcherClient;
import com.evaluacion.dispatcher.client.dto.CallDto;

public class ThreadCreateCall extends Thread{
	
	private DispatcherClient dispatcherClient;
	
	private String url;
	
	public ThreadCreateCall(DispatcherClient dispatcherClient,String url) {
		super();
		this.dispatcherClient=dispatcherClient;
		this.url=url;
	}
	
	public void run() {
		Random rn1 = new Random();
		int time = rn1.nextInt(10 - 5 + 1) + 5;
		Random rn2 = new Random();
		int idcall = rn2.nextInt(500 - 100  + 1) + 100;
		CallDto callDto=new CallDto();
		callDto.setId_call("T"+idcall);
		callDto.setFrom("75236"+idcall);
		callDto.setTo(idcall+"58693");
		String idcallgen=dispatcherClient.dispatch(callDto, url+"dispatch");
		try {
			TimeUnit.SECONDS.sleep(time);
		}catch(Exception e) {
				e.printStackTrace();
		}
		dispatcherClient.freeCall(idcallgen, url+"freeCall");
	}
}

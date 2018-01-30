package com.evaluacion.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.dispatcher.core.dto.CallDto;
import com.evaluacion.dispatcher.core.exception.DispatcherException;
import com.evaluacion.dispatcher.core.manager.CallManager;



@RestController
public class DispatcherController {
	@Autowired
	private CallManager callManager;
	
	
	@RequestMapping(value="/dispatch", method = RequestMethod.POST)
	public String dispatch(@RequestBody CallDto calldto) throws DispatcherException {
		CallDto callJmsDto=new CallDto();
		callJmsDto.setId_call(calldto.getId_call());
		callJmsDto.setFrom(calldto.getFrom());
		callJmsDto.setTo(calldto.getTo());
		return callManager.manageCall(calldto);
		
	}
	
	
	@RequestMapping(value="/freeCall", method = RequestMethod.GET)
	public String  freeCall(@RequestParam(value="idCall") String idCall) throws NumberFormatException, DispatcherException {
		callManager.freeCall(new Integer(idCall)); 
		return "OK";
	}
	

}

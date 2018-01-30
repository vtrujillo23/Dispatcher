package com.evaluacion.dispatcher.core.exception;

public class DispatcherException extends Exception{
	private String msg;
	
	public DispatcherException(String msg) {
		super(new Exception(msg));
	}
	public DispatcherException(String msg,Exception e) {
	    
		super(new Exception(msg,e));
	    
	}
	
}

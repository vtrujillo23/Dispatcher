package com.evaluacion.dispatcher.core.dto;

import java.io.Serializable;

public class CallDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id_call;
	private String from;
	private String to;
	
	
	public String getId_call() {
		return id_call;
	}
	public void setId_call(String id_call) {
		this.id_call = id_call;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	

}

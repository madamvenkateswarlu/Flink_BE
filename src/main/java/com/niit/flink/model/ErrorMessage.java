package com.niit.flink.model;

import javax.persistence.Transient;

import org.springframework.stereotype.Component;


@Component
public class ErrorMessage {
	
	@Transient
	private String error;
	@Transient
	private String code;
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}

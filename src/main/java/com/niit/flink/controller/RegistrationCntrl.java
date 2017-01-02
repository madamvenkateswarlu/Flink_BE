package com.niit.flink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.flink.dao.RegisterDao;
import com.niit.flink.model.Registration;

@RestController
public class RegistrationCntrl {
	
          @Autowired
          RegisterDao rdao;
          
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerTheUser(@RequestBody Registration register){
		

		rdao.save_reg(register);
	    String message="Success";
		
		return message;
		
		
	}
	
}

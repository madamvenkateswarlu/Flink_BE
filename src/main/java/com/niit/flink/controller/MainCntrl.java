package com.niit.flink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.flink.dao.RegisterDao;
import com.niit.flink.model.UserDetails;

@Controller
public class MainCntrl {
	
	@Autowired
	RegisterDao rdao;
	
	@Autowired
	UserDetails userModel;
	
	
	@RequestMapping("/")
	public String getView(){
		return "index";
		
		
	}
	@RequestMapping(value="/entry",method=RequestMethod.POST)
	public String pushAEntry(@RequestParam("username")String username,@RequestParam("password")String pass){
		
		System.out.println(username+""+pass);
		
		userModel.setUsername(username);
		userModel.setPassword(pass);
		
		
		return "index";
		
		
	}

}

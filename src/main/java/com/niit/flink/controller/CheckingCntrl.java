package com.niit.flink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.flink.dao.checkingdao;
import com.niit.flink.model.checkdo;

@Controller
public class CheckingCntrl {
	
	@Autowired
	checkingdao cdao;
	
	@Autowired
	checkdo check;
	
	
	@RequestMapping("/")
	public String getView(){
		return "index";
		
		
	}
	@RequestMapping(value="/entry",method=RequestMethod.POST)
	public String pushAEntry(@RequestParam("username")String user,@RequestParam("password")String pass){
		
		System.out.println(user+""+pass);
		
		check.setUsername(user);
		check.setPassword(pass);
		
		cdao.save_check(check);
		
		return "index";
		
		
	}

}

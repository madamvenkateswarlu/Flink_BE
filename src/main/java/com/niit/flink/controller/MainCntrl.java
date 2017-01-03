package com.niit.flink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MainCntrl {
	

	
	
	@RequestMapping("/")
	public String getView(){
		return "index";
		
		
	}
	@RequestMapping(value="/entry",method=RequestMethod.POST)
	public String pushAEntry(@RequestParam("username")String username,@RequestParam("password")String pass){
		
		System.out.println(username+""+pass);
		
		
		
		
		return "index";
		
		
	}

}

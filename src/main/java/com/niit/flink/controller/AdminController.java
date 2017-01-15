package com.niit.flink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.flink.dao.AdminDao;
import com.niit.flink.model.UserDetails;

@RestController
public class AdminController {
	
	@Autowired
	AdminDao admindao;
	
	@Autowired
	UserDetails userdetails;
	
	@RequestMapping(value="/status/{username}/{status}",method=RequestMethod.GET)
	public ResponseEntity<UserDetails> statusUpdate(@PathVariable("username")String username,@PathVariable("status")String status){
		
	UserDetails user=admindao.statusUpdate(username, status);
	if(user!=null){
	       user.setError(status+"Successfully");
	       user.setCode("200");
			return new ResponseEntity<UserDetails>(user,HttpStatus.OK);

	        }
	        else{
	        	user=userdetails;
	        	user.setError("Something went wrong");
			       user.setCode("404");
				return new ResponseEntity<UserDetails>(user,HttpStatus.OK);

	        }
	}
		     

	@RequestMapping(value="/role/{username}/{role}",method=RequestMethod.GET)
	public ResponseEntity<UserDetails> roleUpdate(@PathVariable("username")String username,@PathVariable("role")String role){
		
	UserDetails user=admindao.RoleUpadte(username, role);
	if(user!=null){
	       user.setError(role+"Successfully");
	       user.setCode("200");
			return new ResponseEntity<UserDetails>(user,HttpStatus.OK);

	        }
	        else{
	        	user=userdetails;
	        	user.setError("Something went wrong");
			       user.setCode("404");
				return new ResponseEntity<UserDetails>(user,HttpStatus.OK);

	        }
	        
		
		
         		
		
		
	}

}

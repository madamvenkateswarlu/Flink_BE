package com.niit.flink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.flink.dao.UserDao;
import com.niit.flink.model.UserDetails;

@RestController
public class UserController {
	
	
	@Autowired
	UserDao userdao;
	
	//register the user
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public ResponseEntity<UserDetails> register(@RequestBody UserDetails user){
		
		userdao.save_user(user);
		
		
		return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
		
		}
	//getting particular row from db
	
	@RequestMapping("/updating/{email}")
	public ResponseEntity<UserDetails>  getParticularId(@PathVariable("email")String email){
		
	UserDetails userrow=userdao.getUpdatingId(email);
		
		return new ResponseEntity<UserDetails>(userrow,HttpStatus.OK);
		
		
		
	}
	@RequestMapping(value="/updated",method=RequestMethod.PUT)
	public  ResponseEntity<UserDetails> UpdatingParticularId(@RequestBody UserDetails user){
		
		  
         userdao.update_user(user);
		
		
		
		return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
		
	}

	
	
}

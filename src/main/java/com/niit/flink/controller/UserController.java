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
	
	@Autowired
	UserDetails userdetails;
	
	//register the user
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public ResponseEntity<UserDetails> register(@RequestBody UserDetails user){
		user.setError("Registration Successfull");
		user.setCode("200");
		user.setIs_online("offline");
		user.setStatus("waiting");
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
	
	@RequestMapping(value="/allusers")
	public ResponseEntity<List<UserDetails>> getAllUsers(){
		
	List<UserDetails> users=userdao.get_All_Users();
	return new ResponseEntity<List<UserDetails>>(users,HttpStatus.OK);
		
		
		
		
	}
	@RequestMapping(value="/loginAuthentication")
	public ResponseEntity<UserDetails> loginAuthentication(@RequestBody UserDetails user){
		UserDetails loggedinuser=userdao.login_Authentication(user);
		        if(loggedinuser!=null){
		       loggedinuser.setError("Logged in Successfully");
		       loggedinuser.setCode("200");
				return new ResponseEntity<UserDetails>(loggedinuser,HttpStatus.OK);

		        }
		        else{
		        	loggedinuser=userdetails;
		        	loggedinuser.setError("Something went wrong");
				       loggedinuser.setCode("404");
					return new ResponseEntity<UserDetails>(loggedinuser,HttpStatus.OK);

		        }
		        
			     

		

		
		
	}

	
	
}

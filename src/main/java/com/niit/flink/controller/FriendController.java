package com.niit.flink.controller;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.flink.dao.FriendDao;
import com.niit.flink.model.Friend;
import com.niit.flink.model.UserDetails;

@RestController
public class FriendController {
	
	@Autowired
	FriendDao fdao;
	
	@Autowired
	Friend frnd;
	@Autowired
	UserDetails udetails;
	
	@RequestMapping(value="friendrequest",method=RequestMethod.POST)
     public ResponseEntity<Friend> friendRequest(@RequestBody Friend f){
		String uid=UUID.randomUUID().toString();
		f.setId(uid);
	   fdao.sendRequest(f);
	return new ResponseEntity<Friend>(f,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="friends",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<String>> fetchFriendlist(HttpSession session) {
		
		udetails=(UserDetails) session.getAttribute("loggedinUser");
		
		ArrayList<String> flist=fdao.fetchFriendList(udetails.getFname());
		
		return new ResponseEntity<ArrayList<String>>(flist,HttpStatus.OK);
		
	}
	
}

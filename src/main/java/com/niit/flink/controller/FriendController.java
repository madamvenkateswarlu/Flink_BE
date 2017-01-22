package com.niit.flink.controller;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/friendrequest",method=RequestMethod.POST)
     public ResponseEntity<Friend> friendRequest(@RequestBody Friend f,HttpSession session){
		udetails=(UserDetails) session.getAttribute("loggedinUser");

		String uid=UUID.randomUUID().toString();
		f.setId(uid);
		f.setUserid(udetails.getUsername());
		f.setStatus("waiting");
	   fdao.sendRequest(f);
	return new ResponseEntity<Friend>(f,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/friends",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Friend>> fetchFriendlist(HttpSession session) {
		
		udetails=(UserDetails) session.getAttribute("loggedinUser");
		if(udetails!=null){
		ArrayList<Friend> flist=fdao.objectFriendList(udetails.getUsername());
		return new ResponseEntity<ArrayList<Friend>>(flist,HttpStatus.OK);

		}
		ArrayList<Friend> flist=new ArrayList<Friend>();

		return new ResponseEntity<ArrayList<Friend>>(flist,HttpStatus.OK);
		
	}
	@RequestMapping(value="/newFriendDisplay",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<String>> fetchFriendDisplay(HttpSession session) {
		
		udetails=(UserDetails) session.getAttribute("loggedinUser");
		if(udetails!=null){
		ArrayList<String> fdlist=fdao.fetchFriends(udetails.getUsername());
		return new ResponseEntity<ArrayList<String>>(fdlist,HttpStatus.OK);

		}
		ArrayList<String> fdlist=new ArrayList<String>();
		return new ResponseEntity<ArrayList<String>>(fdlist,HttpStatus.OK);

	}
	
	@RequestMapping(value="/updateStatus/{id}/{status}",method=RequestMethod.PUT)
	public ResponseEntity<Friend> updateFriendStatus(@PathVariable("id")String id,@PathVariable("status")String status){
		
	Friend fd=fdao.friendStatusUpdate(id, status);
		
		return new ResponseEntity<Friend>(fd,HttpStatus.OK);
		
		
	}
	
}

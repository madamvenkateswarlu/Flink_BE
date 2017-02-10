package com.niit.flink.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.niit.flink.imageupload.ImageUpload;
import com.niit.flink.model.UserDetails;

@RestController
public class ImageUploadingController {
	
		
	String path="E:\\flink workspace\\Flink_FE\\WebContent\\Resources\\userimage";
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public ResponseEntity<String> imageUpload(@RequestPart("file")MultipartFile part_img,HttpSession session){
		
		UserDetails user=(UserDetails) session.getAttribute("loggedinUser");
		
		
		ImageUpload.UploadMethod(path, part_img, user.getUsername()+".jpg");
		
	
		return new ResponseEntity<String>("Successful",HttpStatus.OK);
		}
	
	@RequestMapping(value="/registerupload",method=RequestMethod.POST)
	public ResponseEntity<String> imageRegisterUpload(@RequestPart("file")MultipartFile part_img,@RequestParam("user")String username,HttpSession session){
		
		ImageUpload.UploadMethod(path, part_img, username+".jpg");
	
	
		return new ResponseEntity<String>("Successful",HttpStatus.OK);
		
		
	}
	@RequestMapping(value="/coverupload",method=RequestMethod.POST)
public ResponseEntity<String> coverUpload(@RequestPart("file")MultipartFile part_img,HttpSession session){
		
		UserDetails user=(UserDetails) session.getAttribute("loggedinUser");
		
		
		ImageUpload.UploadMethod(path, part_img, user.getUsername()+"_cover"+".jpg");
		
	
		return new ResponseEntity<String>("Successful",HttpStatus.OK);
		}
	

}

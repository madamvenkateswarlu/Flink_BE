package com.niit.flink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.flink.dao.BlogDao;
import com.niit.flink.model.Blog;

@RestController
public class BlogController {

	@Autowired
	BlogDao blogdao;
	
	@RequestMapping(value="createblog",method=RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
		
		blogdao.save_blog(blog);
		
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		
	}
	
	
}

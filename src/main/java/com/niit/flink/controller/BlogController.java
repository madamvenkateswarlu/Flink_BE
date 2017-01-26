package com.niit.flink.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.flink.dao.BlogDao;
import com.niit.flink.model.Blog;
import com.niit.flink.model.UserDetails;

@RestController
public class BlogController {

	@Autowired
	BlogDao blogdao;
	
	@Autowired
	Blog blog;
	

	
	@RequestMapping(value="createblog",method=RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog,HttpSession session){
		String random_id = UUID.randomUUID().toString();
         blog.setBlog_id(random_id);
         blog.setLike_blog("0");
         blog.setDislike_blog("0");
		blogdao.save_blog(blog);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="userBlog",method=RequestMethod.GET)
	 public ResponseEntity<ArrayList<Blog>> userBlog(HttpSession session)
	 {

        UserDetails user=(UserDetails) session.getAttribute("loggedinUser");
         if(user!=null){
		
		ArrayList<Blog> blogwithuser=blogdao.fetchWithUser(user.getUsername());
		
		return new ResponseEntity<ArrayList<Blog>>(blogwithuser,HttpStatus.OK);
         }
         else{
        	 blog.setCode("404");
        	 blog.setError("session invalidate");
        	 ArrayList<Blog> blogwithuser=new ArrayList<Blog>();
        	 blogwithuser.add(blog);
        	 
     		return new ResponseEntity<ArrayList<Blog>>(blogwithuser,HttpStatus.OK);
 
         }
           
      }
   @SuppressWarnings("unchecked")
	@RequestMapping(value="deleteBlog/{blog_id}",method=RequestMethod.GET)
	 public ResponseEntity<Blog> deleteBlog(@PathVariable("blog_id")String blog_id,HttpSession session)
	 {

         
		         
		         Blog deleting_blog=blogdao.deleteBlog(blog_id);
		         deleting_blog.setCode("200");
	        	 deleting_blog.setError("deleted successfully");
	        	 
		         
		return new ResponseEntity<Blog>(deleting_blog,HttpStatus.OK);
         
         
           }
   @RequestMapping(value="editBlog/{blog_id}",method=RequestMethod.GET)
   public ResponseEntity<Blog> editBlog(@PathVariable("blog_id")String blog_id){
	    
	  Blog b= blogdao.fetchParticularBlog(blog_id);
	   
	return new ResponseEntity<Blog>(b,HttpStatus.OK);
	   
   }
	
	@RequestMapping(value="createblog",method=RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog,HttpSession session){
		
		Blog b=blogdao.updateBlog(blog);
		return new ResponseEntity<Blog>(b,HttpStatus.OK);
		
	}
	@RequestMapping(value="allBlog",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Blog>> allBlog(){
		
		ArrayList<Blog> b =blogdao.allBlog();
		
		return new ResponseEntity<ArrayList<Blog>>(b,HttpStatus.OK);
		
	}
	
	 }


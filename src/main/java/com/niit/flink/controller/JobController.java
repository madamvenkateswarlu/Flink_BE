package com.niit.flink.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.google.gson.Gson;
import com.niit.flink.dao.JobDao;
import com.niit.flink.model.Job;
import com.niit.flink.model.Jobapplied;
import com.niit.flink.model.UserDetails;

@RestController
public class JobController{
	
	@Autowired
	JobDao jdao;
	@Autowired
	Job job;
	
@Autowired
Jobapplied jobap;
	
	@RequestMapping(value="/postJob",method=RequestMethod.POST)
	public ResponseEntity<Job> saveJob(@RequestBody Job j){
		String random_id = UUID.randomUUID().toString();
        j.setId(random_id);
        Date date = new Date();
       /* DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);*/
        j.setDate_time(date);
        jdao.saveJob(j);
	return new ResponseEntity<Job>(j,HttpStatus.OK);
		}
	
	@RequestMapping(value="/allJob",method=RequestMethod.GET)
    public ResponseEntity<ArrayList<Job>> allJobs(){
		ArrayList<Job> allJobs=new ArrayList<Job>();
		         allJobs=jdao.fetchJobs();
		
		return new ResponseEntity<ArrayList<Job>>(allJobs,HttpStatus.OK);
		}
	@RequestMapping(value="/getJob/{id}",method=RequestMethod.GET)
	public ResponseEntity<Job>getJob(@PathVariable("id")String id){
		
		Job j=jdao.getParticularJob(id);
		
		return new ResponseEntity<Job>(j,HttpStatus.OK);
	}
	@RequestMapping(value="/updateJob",method=RequestMethod.PUT)
	public ResponseEntity<Job>updateJob(@RequestBody Job j){
		
		Job job=jdao.updateJob(j);
		
		return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
	@RequestMapping(value="/deleteJob/{id}",method=RequestMethod.GET)
	public ResponseEntity<Job>deleteJob(@PathVariable("id")String id){
		
		Job job=jdao.deleteJob(id);
		
		return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
	
	@RequestMapping(value="/applyJob",method=RequestMethod.POST)
	public ResponseEntity<Jobapplied> applyJob(@RequestBody Jobapplied j,HttpSession session){
		
		String random_id = UUID.randomUUID().toString();
        UserDetails user=(UserDetails) session.getAttribute("loggedinUser");
       
        Date date = new Date();
  if( user!=null){
            j.setUsername(user.getUsername());
            j.setId_job(random_id);
            j.setAdate(date);
          Job b=jdao.getParticularJob(j.getJobid());
            Gson gson=new Gson();
             j.setJob(gson.toJson(b));  
        	if(jdao.applyJob(j)){
             j.setCode("200");
        	 j.setError("Job applied successfully");
         	return new ResponseEntity<Jobapplied>(j,HttpStatus.OK);
         	}
        	else{
            	j=jobap;
            	j.setCode("404");
           	    j.setError("already job applied");
            	return new ResponseEntity<Jobapplied>(j,HttpStatus.OK);
            	}
        	}
        else{
        	j=jobap;
        	j.setCode("404");
       	    j.setError("Session Expired");
        	return new ResponseEntity<Jobapplied>(j,HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        
		}
	
	@RequestMapping(value="/userJob",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Job>> jobAppliedUser(HttpSession session){
        UserDetails user=(UserDetails) session.getAttribute("loggedinUser");

		ArrayList<Job> ja=jdao.getJobAppliedWithUsername(user.getUsername());
		
		return new ResponseEntity<ArrayList<Job>>(ja,HttpStatus.OK);
		
	}
}

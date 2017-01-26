package com.niit.flink.dao;

import java.util.ArrayList;

import com.niit.flink.model.Job;

public interface JobDao {
	
	public boolean saveJob(Job j);
	public ArrayList<Job> fetchJobs();
	public Job getParticularJob(String Id);
	public Job updateJob(Job j);
	public Job deleteJob(String id);
		
	

}

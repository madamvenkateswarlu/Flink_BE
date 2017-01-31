package com.niit.flink.dao;

import java.util.ArrayList;

import com.niit.flink.model.Job;
import com.niit.flink.model.Jobapplied;

public interface JobDao {
	//job
	public boolean saveJob(Job j);
	public ArrayList<Job> fetchJobs();
	public Job getParticularJob(String Id);
	public Job updateJob(Job j);
	public Job deleteJob(String id);
	
	//job applied
	public boolean applyJob(Jobapplied ja);
	public ArrayList<Job> getJobAppliedWithUsername(String username);
	public ArrayList<Jobapplied> allgetJobApplied();
	public Jobapplied updateStatus(String id,String status);

	

}

package com.niit.flink.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;
@Entity
@Table
@Component
public class Jobapplied extends ErrorMessage {
	@Id
	private String id_job;
	
	private String username;
	private String jobid;
	private Date  adate;
	private String status_job;
	
	private String jobtitle;
	
	@Transient
	private Job job;
	
	
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getId_job() {
		return id_job;
	}
	public void setId_job(String id_job) {
		this.id_job = id_job;
	}
	public Date getAdate() {
		return adate;
	}
	public void setAdate(Date adate) {
		this.adate = adate;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String getStatus_job() {
		return status_job;
	}
	public void setStatus_job(String status_job) {
		this.status_job = status_job;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	
	

}

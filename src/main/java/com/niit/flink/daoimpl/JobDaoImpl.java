package com.niit.flink.daoimpl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.flink.dao.JobDao;
import com.niit.flink.model.Job;

@Repository("JobDao")
@Transactional
public class JobDaoImpl implements JobDao{
	
	@Autowired 
	SessionFactory sessionFactory;
	
	public JobDaoImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory=sessionFactory;
		
	}

	public boolean saveJob(Job j) {
		
		sessionFactory.getCurrentSession().save(j);
		
		return false;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Job> fetchJobs() {
		ArrayList<Job> fetchjob=new ArrayList<Job>();
		fetchjob=(ArrayList<Job>) sessionFactory.getCurrentSession().createCriteria(Job.class).list();
		return fetchjob;
	}

	public Job getParticularJob(String Id) {
		Job j=(Job) sessionFactory.getCurrentSession().get(Job.class, Id);
		return j;
	}

	public Job updateJob(Job j) {
		sessionFactory.getCurrentSession().update(j);
		
		return j;
	}

	public Job deleteJob(String id) {
		Job j=getParticularJob(id);
		sessionFactory.getCurrentSession().delete(j);

		return j;
	}

}

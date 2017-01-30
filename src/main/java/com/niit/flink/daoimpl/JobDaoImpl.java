package com.niit.flink.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.niit.flink.dao.JobDao;
import com.niit.flink.model.Job;
import com.niit.flink.model.Jobapplied;

@Repository("JobDao")
@Transactional
public class JobDaoImpl implements JobDao{
	
	@Autowired
	Jobapplied ja;
	
	@Autowired 
	SessionFactory sessionFactory;
	public JobDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
		}
    public boolean saveJob(Job j) {
		try {
			sessionFactory.getCurrentSession().save(j);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;

		}
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
                               /*Job Applied*/

	@SuppressWarnings("unchecked")
	public boolean applyJob(Jobapplied ja) {
		try {
			String jobid=ja.getJobid();
			String username=ja.getUsername();
			String hql="from Jobapplied where jobid ='"+jobid+"' and username='"+username+"'";
			Query q=sessionFactory.getCurrentSession().createQuery(hql);
			ArrayList<Jobapplied> job=new ArrayList<Jobapplied>();
			job=(ArrayList<Jobapplied>) q.list();
			if(job.isEmpty()){
			sessionFactory.getCurrentSession().save(ja);
			return true;
			}
			else{
				return false;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		}
	@SuppressWarnings("unchecked")
	public ArrayList<Job> getJobAppliedWithUsername(String username) {
		
		ProjectionList p1=Projections.projectionList();
        p1.add(Projections.property("jobid"));
        p1.add(Projections.property("status_job"));
        p1.add(Projections.property("adate"));
		Criteria cr=sessionFactory.getCurrentSession().createCriteria(Jobapplied.class).setProjection(p1);
		cr.add(Restrictions.like("username", username));
		ArrayList<Object> list=new ArrayList<Object>();
		list=(ArrayList<Object>) cr.list();
		Iterator<Object> iter=list.iterator();
		ArrayList<Job> objlist=new  ArrayList<Job>();
		while (iter.hasNext()) {
			Object ob[] = (Object[])iter.next();
		  Job j =getParticularJob((String) ob[0]);
		j.setJob_status((String) ob[1]);
		j.setJob_date((Date) ob[2]);
	     objlist.add(j);
		}
		
		
		return objlist;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Jobapplied> allgetJobApplied() {
		
		
		 ArrayList<Jobapplied> jobs=(ArrayList<Jobapplied>) sessionFactory.getCurrentSession().createCriteria(Jobapplied.class).list();
			
		return jobs;		
	}
	}
	
	
	


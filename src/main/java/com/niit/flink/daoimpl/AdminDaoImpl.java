package com.niit.flink.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.flink.dao.AdminDao;
import com.niit.flink.model.UserDetails;

@Repository("AdminDao")
@Transactional
public class AdminDaoImpl implements AdminDao {
	
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	
	public AdminDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	

	public UserDetails statusUpdate(String username,String staus) {
		
		UserDetails user=(UserDetails) sessionFactory.getCurrentSession().get(UserDetails.class, username);
		if(user!=null){
		user.setStatus(staus);
		sessionFactory.getCurrentSession().update(user);
		
		}
		
		
		
		//String statusql="from Userdetails where username='"+username+"'";
		
		return user;
	}

}

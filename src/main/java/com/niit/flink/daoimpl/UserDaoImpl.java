package com.niit.flink.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.flink.dao.UserDao;
import com.niit.flink.model.UserDetails;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	
	
	public UserDaoImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory=sessionFactory;
		
		
	}

	public boolean save_user(UserDetails user) {
		
		sessionFactory.getCurrentSession().save(user);
		
	
		return false;
	}

	public UserDetails getUpdatingId(String user) {
		
		return (UserDetails) sessionFactory.getCurrentSession().get( UserDetails.class,user);
	}

	public boolean update_user(UserDetails user) {
		
		sessionFactory.getCurrentSession().update(user);
		
		return false;
	}
	
	

}

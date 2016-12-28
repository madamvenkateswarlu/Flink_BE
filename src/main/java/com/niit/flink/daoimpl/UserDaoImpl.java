package com.niit.flink.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.flink.dao.UserDao;
import com.niit.flink.model.UserDetails;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {
	
@Autowired
SessionFactory sessionFactory;

public UserDaoImpl(SessionFactory sessionFactory) {
	
	this.sessionFactory=sessionFactory;
	
}

	
	@Transactional
	public boolean save_check(UserDetails user) {
		
		sessionFactory.getCurrentSession().save(user);
		
		return true;
	}

}

package com.niit.flink.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.flink.dao.checkingdao;
import com.niit.flink.model.checkdo;

@Repository("checkingdao")
public class checkingdoaimpl implements checkingdao {
	
@Autowired
SessionFactory sessionFactory;

public checkingdoaimpl(SessionFactory sessionFactory) {
	
	this.sessionFactory=sessionFactory;
	
}

	
	@Transactional
	public boolean save_check(checkdo check) {
		
		sessionFactory.getCurrentSession().save(check);
		
		return true;
	}

}

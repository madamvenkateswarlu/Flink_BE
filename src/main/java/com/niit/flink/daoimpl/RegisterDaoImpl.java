package com.niit.flink.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.flink.dao.RegisterDao;
import com.niit.flink.model.Registration;
import com.niit.flink.model.UserDetails;

@Repository("RegisterDao")
public class RegisterDaoImpl implements RegisterDao {
	
@Autowired
SessionFactory sessionFactory;

public RegisterDaoImpl(SessionFactory sessionFactory) {
	
	this.sessionFactory=sessionFactory;
	
}

	
	@Transactional
	public boolean save_reg(Registration reg) {
		
		sessionFactory.getCurrentSession().save(reg);
		
		return true;
	}
	@Transactional
	public List<Registration> getList(){
		
	@SuppressWarnings({ "unchecked", "deprecation" })
	List<Registration> userList=sessionFactory.getCurrentSession().createCriteria(Registration.class).list();
		System.out.println(userList);
		return userList;
		
		
	}

}

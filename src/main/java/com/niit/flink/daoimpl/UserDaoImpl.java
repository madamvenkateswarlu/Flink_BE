package com.niit.flink.daoimpl;

import java.util.List;

import org.hibernate.Query;
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

	public List<UserDetails> get_All_Users() {
		
		@SuppressWarnings("unchecked")
		List<UserDetails> userslist= sessionFactory.getCurrentSession().createCriteria(UserDetails.class).list();
		
		return userslist;
	}

	public UserDetails login_Authentication(UserDetails user) {
		String username=user.getUsername();
		String password=user.getPassword();
		String hqlquery="from  UserDetails where username='"+username+"' and password='"+password+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hqlquery);
         UserDetails loggedUser=(UserDetails) query.uniqueResult();
		return loggedUser;
	}
	
	

}

package com.niit.flink.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.flink.dao.BlogDao;
import com.niit.flink.model.Blog;

@Repository("BlogDao")
@Transactional
public class BlogDaoImpl implements BlogDao{
	
	@Autowired
	SessionFactory sessionFactory;

	public BlogDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	
	public boolean save_blog(Blog blog) {
		
		sessionFactory.getCurrentSession().save(blog);
		
		return true;
	}

}

package com.niit.flink.daoimpl;

import java.util.ArrayList;
import java.util.List;

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


	public ArrayList<Blog> fetchWithUser(String username) {
	
		@SuppressWarnings("unchecked")
		
		ArrayList<Blog> blogwithuser=(ArrayList<Blog>) sessionFactory.getCurrentSession().createCriteria(Blog.class,username).list();
		
		return blogwithuser;
	}


	public Blog deleteBlog(String blog_id) {
		
		Blog b=(Blog) sessionFactory.getCurrentSession().get(Blog.class, blog_id);
		
		sessionFactory.getCurrentSession().delete(b);
		
		
		return b;
	}


	public Blog fetchParticularBlog(String blog_id) {
		Blog b=(Blog) sessionFactory.getCurrentSession().get(Blog.class, blog_id);

		
		
		return b;
	}


	public Blog updateBlog(Blog b) {
		sessionFactory.getCurrentSession().update(b);

		return b;
	}

}

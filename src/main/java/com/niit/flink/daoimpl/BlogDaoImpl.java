package com.niit.flink.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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
	
		String hql="from Blog where writtenby='"+username+"'";
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		ArrayList<Blog> blogwithuser=(ArrayList<Blog>) q.list();
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


	@SuppressWarnings("unchecked")
	public ArrayList<Blog> allBlog() {
		ArrayList<Blog> allBlog=new ArrayList<Blog>();
	allBlog=(ArrayList<Blog>) sessionFactory.getCurrentSession().createCriteria(Blog.class).list();
		return allBlog;
	}

}

package com.niit.flink.dao;

import java.util.ArrayList;
import java.util.List;

import com.niit.flink.model.Blog;

public interface BlogDao {
	
	public boolean save_blog(Blog blog);	
	public ArrayList<Blog> fetchWithUser(String username);
	public Blog deleteBlog(String blog_id);
	public Blog fetchParticularBlog(String blog_id);
	public Blog updateBlog(Blog b);
}
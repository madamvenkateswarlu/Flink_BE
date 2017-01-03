package com.niit.flink.dao;

import com.niit.flink.model.UserDetails;

public interface UserDao {
	public boolean save_user(UserDetails user);
	public UserDetails getUpdatingId(String email);
	public boolean update_user(UserDetails user);
	
	

}

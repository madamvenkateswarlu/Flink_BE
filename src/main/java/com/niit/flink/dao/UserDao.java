package com.niit.flink.dao;

import java.util.List;

import com.niit.flink.model.UserDetails;

public interface UserDao {
	public boolean save_user(UserDetails user);
	public UserDetails getUpdatingId(String email);
	public boolean update_user(UserDetails user);
	public List<UserDetails> get_All_Users();
	public UserDetails login_Authentication(UserDetails user);
	

}

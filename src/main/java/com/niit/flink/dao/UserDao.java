package com.niit.flink.dao;

import com.niit.flink.model.UserDetails;

public interface UserDao {

	public boolean save_check(UserDetails user);
}

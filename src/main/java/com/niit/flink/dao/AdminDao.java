package com.niit.flink.dao;

import com.niit.flink.model.UserDetails;

public interface AdminDao {
	
	public UserDetails statusUpdate(String username,String status);

}

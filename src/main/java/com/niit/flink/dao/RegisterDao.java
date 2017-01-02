package com.niit.flink.dao;

import java.util.List;

import com.niit.flink.model.Registration;

public interface RegisterDao {

	public boolean save_reg(Registration register);
	public List<Registration> getList();
}

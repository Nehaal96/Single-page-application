package com.niit.dao;

import java.util.List;

import com.niit.model.UserDetail;

public interface UserDao {

	public List<UserDetail> getAllUsers();
	
	public boolean saveUser(UserDetail user);
	public UserDetail getUser(String username);
	public boolean updateOnlineStatus(String status,UserDetail user);
	public boolean checkLogin (UserDetail user);
	
}

package com.eventview.service;

import java.util.List;

import com.eventview.model.Users;

public interface UserService {
	
	List<Users> getAllUsers();

	Users findByUserId(Integer user_id);

	Users createUser(Users user);

	Users updateUser(Users user);

	Users deleteUser(Users user);
 
}

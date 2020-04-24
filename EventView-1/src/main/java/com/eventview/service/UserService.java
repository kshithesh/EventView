package com.eventview.service;

import java.util.List;

import com.eventview.model.Users;

public interface UserService {

	List<Users> getAllUsers();

	Users findByUserId(Integer user_id);

	void createUser(Users users);

	void updateUser(Users users);

	void deleteUser(Integer user_id);

	boolean exists(Users users);
}

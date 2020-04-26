package com.eventview.service;

import java.util.List;

import com.eventview.model.Users;

public interface UserService {

	List<Users> getAllUsers();

	Users findByUserId(Integer userid);

	void createUser(Users users);

	void updateUser(Users users);

	void deleteUser(Integer userid);

	boolean exists(Users users);
}

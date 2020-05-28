package com.eventview.service;

import java.util.List;

import com.eventview.model.Users;

public interface UserService {

	List<Users> getAllUsers();

	Users findByUserId(Integer userId);

	void createUser(Users users);

	void updateUser(Users users);

	void deleteUser(Integer userId);

	boolean exists(Users users);

	List<String> getFNameToday();

	List<String> getFNameWeek();
}

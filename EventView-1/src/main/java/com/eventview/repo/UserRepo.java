package com.eventview.repo;

import java.util.List;

import com.eventview.model.Users;

public interface UserRepo {

	List<Users> getAllUsers();

	Users findByUserId(Integer userId);

	void createUser(Users users);

	void updateUser(Users users);

	int deleteUser(Integer userId);

	boolean userExists(Integer userId);

	List<String> getEmailByEvent(Integer eventId);

	List<String> getFNameWeek();

	List<String> getFNameToday();
}

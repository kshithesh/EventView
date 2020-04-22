package com.eventview.repo;

import java.util.List;

import com.eventview.model.Users;

public interface UserRepo {

	List<Users> getAllUsers();

	Users findByUserId(Integer user_id);

	Users createUser(Users user);

	Users updateUser(Users user);

	void deleteUser(Integer user_id);
}

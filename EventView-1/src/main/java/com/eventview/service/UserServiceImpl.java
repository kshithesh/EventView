package com.eventview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventview.model.Users;
import com.eventview.repo.UserRepo;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public List<Users> getAllUsers(){
		return userRepo.getAllUsers();
	}
	
	@Override
	public Users findByUserId(Integer user_id) {
		return userRepo.findByUserId(user_id);
	}

	@Override
	public Users createUser(Users user) {
		return userRepo.createUser(user);
	}

	@Override
	public Users updateUser(Users user) {
		return userRepo.updateUser(user);
	}

	@Override
	public Users deleteUser(Users user) {
		return userRepo.deleteUser(user);
	}
}

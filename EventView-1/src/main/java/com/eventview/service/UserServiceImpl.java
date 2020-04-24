package com.eventview.service;

import com.eventview.model.Users;
import com.eventview.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Users> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public Users findByUserId(Integer user_id) {
        return userRepo.findByUserId(user_id);
    }

    @Override
    public void createUser(Users users) {
        log.info("Service done");
        userRepo.createUser(users);
    }

    @Override
    public void updateUser(Users users) {
        userRepo.updateUser(users);
    }

    @Override
    public void deleteUser(Integer user_id) {
        userRepo.deleteUser(user_id);
    }

    @Override
    public boolean exists(Users users) {
        return findByUserId(users.getUser_id()) != null;
    }
}

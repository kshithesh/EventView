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
    public Users findByUserId(Integer userId) {
        return userRepo.findByUserId(userId);
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
    public void deleteUser(Integer userid) {
        userRepo.deleteUser(userid);
    }

    @Override
    public boolean exists(Users users) {
        return userRepo.userExists(users.getUserId());
    }

    @Override
    public List<String> getFNameToday() {
        return userRepo.getFirstName();
    }

    @Override
    public List<String> getFNameWeek() {
        return userRepo.getFNameWeek();
    }
}


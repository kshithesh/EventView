package com.eventview.repo;

import com.eventview.dao.UserRowMapper;
import com.eventview.exceptions.UserNotFoundException;
import com.eventview.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository("userRepo")
public class UserRepoImpl implements UserRepo {

    private final Logger log = LoggerFactory.getLogger(UserRepoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Users> getAllUsers() {
        String SELECT_ALL_USERS = "SELECT user_id,first_name,last_name,phone,email FROM users";
        return jdbcTemplate.query(SELECT_ALL_USERS, new UserRowMapper());
    }

    @Override
    public Users findByUserId(Integer userId) {
        try {
            String SELECT_USER_BY_ID = "select * from users where user_id = ?";
            Users users = jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new Object[]{userId}, new UserRowMapper());
            log.info("query generated " + SELECT_USER_BY_ID + "-----" + userId);
            return users;
        } catch (Exception e) {
            throw new UserNotFoundException("User not found with id: "+userId);
        }
    }

    @Override
    public void createUser(Users users) {
        String INSERT_USER = "INSERT INTO users (`user_id`, `first_name`, `last_name`, `phone`, `email`) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(INSERT_USER,
                users.getUserId(), users.getFName(), users.getLName(), users.getPhone(), users.getEmail());
        log.info("repo created");
    }

    @Override
    public void updateUser(Users users) {
        try {
            String UPDATE_USER = "update users set first_name=?, last_name=?, phone=?, email=? where user_id=?";
            jdbcTemplate.update(UPDATE_USER,
                    users.getFName(), users.getLName(), users.getPhone(), users.getEmail(), users.getUserId());
        } catch (Exception e) {
            throw new UserNotFoundException("User not found with id: "+users.getUserId());
        }
    }

    @Override
    public int deleteUser(Integer userId) {
        String DELETE_USER = "delete from users where user_id=?";
        Object[] del = new Object[]{userId};
        int size = jdbcTemplate.update(DELETE_USER, del);
        if (size == 0) {
            throw new UserNotFoundException("No User found to delete: " + userId);
        }
        return size;
    }
}

package com.eventview.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eventview.dao.UserRowMapper;
import com.eventview.model.Users;

@Repository("userRepo")
public class UserRepoImpl implements UserRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Users> getAllUsers() {
		String usql = "SELECT user_id,first_name,last_name,phone,email FROM users";
		List<Users> users = jdbcTemplate.query(usql, new UserRowMapper());
		return users;
	}

	@Override
	public Users findByUserId(Integer user_id) {
		String fbui = "select * from users where user_id = ?";
		return jdbcTemplate.queryForObject(fbui, new Object[] { user_id }, new UserRowMapper());
	}

	@Override
	public Users createUser(Users user) {
		jdbcTemplate.update(
				"INSERT INTO users (`user_id`, `first_name`, `last_name`, `phone`, `email`) VALUES (?,?,?,?,?)",
				user.getUser_id(), user.getFirst_name(), user.getLast_name(), user.getPhone(), user.getEmail());
		return user;
	}

	@Override
	public Users updateUser(Users user) {
		jdbcTemplate.update("update users set first_name=?, last_name=?, phone=?, email=? where user_id=?",
				user.getFirst_name(), user.getLast_name(), user.getPhone(), user.getEmail(), user.getUser_id());
		return user;
	}

	@Override
	public Users deleteUser(Users user) {
		jdbcTemplate.update("delete from users where user_id=?", user.getUser_id());
		return user;
	}

}

package com.eventview.repo;

import com.eventview.dao.UserRowMapper;
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
		String usql = "SELECT user_id,first_name,last_name,phone,email FROM users";
		return jdbcTemplate.query(usql, new UserRowMapper());
	}

	@Override
	public Users findByUserId(Integer userid) {
		String fbui = "select * from users where user_id = ?";
		return jdbcTemplate.queryForObject(fbui, new Object[] { userid }, new UserRowMapper());
	}

	@Override
	public void createUser(Users users) {
		jdbcTemplate.update(
				"INSERT INTO users (`user_id`, `first_name`, `last_name`, `phone`, `email`) VALUES (?,?,?,?,?)",
				users.getUserid(), users.getFname(), users.getLname(), users.getPhone(), users.getEmail());
		log.info("repo created");
	}

	@Override
	public void updateUser(Users users) {
		jdbcTemplate.update("update users set first_name=?, last_name=?, phone=?, email=? where user_id=?",
				users.getFname(), users.getLname(), users.getPhone(), users.getEmail(), users.getUserid());
	}

	@Override
	public void deleteUser(Integer userid) {
		Object[] del = new Object[] {userid};
		jdbcTemplate.update("delete from users where user_id=?", del);
		System.out.println("Record with id:"+ userid + " are deleted");
	}
}

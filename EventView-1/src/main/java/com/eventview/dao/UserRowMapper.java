package com.eventview.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventview.model.Users;

public class UserRowMapper implements RowMapper<Users> {


	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		Users user = new Users();
		user.setUserId(rs.getInt("user_id"));
		user.setFName(rs.getString("first_name"));
		user.setLName(rs.getString("last_name"));
		user.setPhone(rs.getString("phone"));
		user.setEmail(rs.getString("email"));
		return user;
	}
	
}


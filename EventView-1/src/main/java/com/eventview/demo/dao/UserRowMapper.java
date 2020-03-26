package com.eventview.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.eventview.demo.model.Users;

public class UserRowMapper implements RowMapper<Users> {


	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		Users user = new Users();
		user.setUser_id(rs.getInt("user_id"));
		user.setFirst_name(rs.getString("first_name"));
		user.setLast_name(rs.getString("last_name"));
		user.setPhone(rs.getString("phone"));
		user.setEmail(rs.getString("email"));
		return user;
	}

}

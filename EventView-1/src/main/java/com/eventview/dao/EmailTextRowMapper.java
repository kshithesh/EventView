package com.eventview.dao;

import com.eventview.model.EmailText;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailTextRowMapper implements RowMapper<EmailText> {

    @Override
    public EmailText mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EmailText(rs.getString("first_name"),rs.getString("event_type"));
    }
}

package com.eventview.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;

	static String first_name;
	static String last_name;
	private String phone;
	private String email;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * public Users(Integer user_id, String first_name, String last_name, String
	 * phone, String email, Timestamp create_timestamp, Timestamp update_timestamp)
	 * { super(); this.user_id = user_id; this.first_name = first_name;
	 * this.last_name = last_name; this.phone = phone; this.email = email;
	 * this.create_timestamp = create_timestamp; this.update_timestamp =
	 * update_timestamp; }
	 */

	/*
	 * @Override public String toString() { return "Users [user_id=" + user_id +
	 * ", first_name=" + first_name + ", last_name=" + last_name + ", phone=" +
	 * phone + ", email=" + email + "]"; }
	 */

}

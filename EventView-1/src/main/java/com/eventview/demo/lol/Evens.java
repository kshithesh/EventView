package com.eventview.demo.lol;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Evens {
	
	@Id
	private String eid;
	private String fname;
	private String lname;
	private String ed;
	private String etypes;
	private String phone;
	private String email;
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEd() {
		return ed;
	}
	public void setEd(String ed) {
		this.ed = ed;
	}
	public String getEtypes() {
		return etypes;
	}
	public void setEtypes(String etypes) {
		this.etypes = etypes;
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
	@Override
	public String toString() {
		return "Evens [eid=" + eid + ", fname=" + fname + ", lname=" + lname + ", ed=" + ed + ", etypes=" + etypes
				+ ", phone=" + phone + ", email=" + email + "]";
	}
}

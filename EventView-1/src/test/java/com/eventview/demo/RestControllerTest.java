package com.eventview.demo;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.eventview.model.Users;

public class RestControllerTest {
	
	@Test
	public void testCreateUser() {
		RestTemplate restTemplate = new RestTemplate();
		
		Users user = new Users();
		user.setUser_id(5);
		user.setFirst_name("Ramu");
		user.setLast_name("Dara");
		user.setEmail("ramudara@gmail.com");
		user.setPhone("9052432668");
		
		restTemplate.put("http://localhost:8080/usercreate", user);
	}

}

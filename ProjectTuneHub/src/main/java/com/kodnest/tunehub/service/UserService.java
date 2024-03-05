package com.kodnest.tunehub.service;


//import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;


public interface UserService {
	public String addUser(User user);
	
	 public String getRole(String email);
	boolean emailExists(String email, String password);
}

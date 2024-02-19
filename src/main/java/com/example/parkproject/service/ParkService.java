package com.example.parkproject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parkproject.entity.UserRegistration;
import com.example.parkproject.repository.UserRepository;

@Service
public class ParkService {
	
	@Autowired
	   private UserRepository userRepository;

	public void saveuserdetails(UserRegistration userRegistration) {
		// TODO Auto-generated method stub
		
		userRepository.save(userRegistration);
		
	}

	public Object findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(username);
	}

	public void saveandupdatetime(UserRegistration user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
		
	}
	
	
	
	
	
	
	

}

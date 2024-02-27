package com.example.parkproject.service;


import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	  LocalDate birth= userRegistration.getDate_of_birth();
	  LocalDate presentdate=LocalDate.now();
	  Period result=Period.between(birth, presentdate);
	  
	  int age=result.getYears();
	  userRegistration.setAge(age);
	  
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
	

	
	public int countpeoplebetweenage() {
		// TODO Auto-generated method stub
		int start=20;
		int end=30;
		List<UserRegistration> getall=userRepository.findAll();
		int count=0;
		for(UserRegistration alluser:getall)
		{
			if(alluser.getAge()>=start&&alluser.getAge()<=end)
			{
                count++;
			}
		}
		return count;
	}

	public Map<String, Integer> countpeoples() {
		// TODO Auto-generated method stub
		
		Map<String,Integer> divideages=new HashMap<>();
		

		List<UserRegistration> getall=userRepository.findAll();
		int fir = 0,so = 0,t = 0;//,fo,fi,six,sev=0;
		for(UserRegistration onebyone:getall)
		{
			if(onebyone.getAge()>=0&&onebyone.getAge()<=10)
			{
				fir++;
				divideages.put("Zero to ten years childers are",fir);
			}
			else if(onebyone.getAge()>=11&&onebyone.getAge()<=20)
			{
				so++;
				divideages.put("eleven to twenty years boy&girls are ",so);
			}
			else if(onebyone.getAge()>=21&&onebyone.getAge()<=30)
			{
				t++;
				divideages.put("twentyone to thiry years people are",t);
			}
			
		}
		
		return divideages;
	}
	
	
	
	
	
	
	

}

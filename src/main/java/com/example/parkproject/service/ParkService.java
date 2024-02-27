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
		int fir = 0,so = 0,t = 0,fo=0,fi=0,six=0,sev=0,ei=0;
		for(UserRegistration onebyone:getall)
		{
			if(onebyone.getAge()>=0&&onebyone.getAge()<=10)
			{
				fir++;
				divideages.put("Zero to ten years age childers count are",fir);
			}
			else if(onebyone.getAge()>=11&&onebyone.getAge()<=20)
			{
				so++;
				divideages.put("eleven to twenty years age boy&girls count are ",so);
			}
			else if(onebyone.getAge()>=21&&onebyone.getAge()<=30)
			{
				t++;
				divideages.put("twentyone to thiry years age people count are",t++);
			}
			else if(onebyone.getAge()>=31&&onebyone.getAge()<=40)
			{
				divideages.put("thirtyone to fourty years age people count are", fo++);
			}
			else if(onebyone.getAge()>=41&&onebyone.getAge()<=50)
			{
				divideages.put("fourtyone to fifty  years age people count are", fi++);
			}
			else if(onebyone.getAge()>=51&&onebyone.getAge()<=60)
			{
				divideages.put("fiftyone to sixty years age people count are", six++);
			}
			else if(onebyone.getAge()>=61&&onebyone.getAge()<=70)
			{
				divideages.put("sixtyone to seventy years age people count are", sev++);
			}
			else 
			{
				ei++;
				divideages.put("seventyone to and above age  people count are", ei);
			}




		}
		
		return divideages;
	}
	
	
	
	
	
	
	

}

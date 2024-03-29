package com.example.parkproject.controller;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.parkproject.entity.UserRegistration;
import com.example.parkproject.service.ParkService;


@RestController
@RequestMapping("/user")
public class ParkController {
	   
	   @Autowired
	   private ParkService parkService;
	
	
	     @PostMapping("/register")
	    public ResponseEntity<?> registerUser(@RequestBody UserRegistration userRegistration) {  	
//	    	 System.out.print(userRegistration.getUsername());
//	        // Check if username is already taken
//	    	UserRegistration present=new UserRegistration();
//	    	 String username=present.getUsername();
////	    	 String comingusername=userRegistration.getUsername();
//		
	        if (parkService.findByUsername(userRegistration.getUsername()) != null)
	         {
	        	
	            return ResponseEntity.badRequest().body("Username is already taken");
	        }
	        else
	        {
	        	 parkService.saveuserdetails(userRegistration);
	        }
	        return ResponseEntity.ok("Registration successful");
	    }
	 
	 
	 
	    @PostMapping("/login")
	    public ResponseEntity<?> loginUser(@RequestParam String userName,@RequestParam String password) {
		 UserRegistration userdata=(UserRegistration) parkService.findByUsername(userName);
	    var user = parkService.findByUsername(userName);
	    userdata.setEntryTime(LocalDateTime.now());
	    parkService.saveandupdatetime(userdata);
	       if (user == null || !((UserRegistration) user).getPassword().equals(password)) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	        }
	        return ResponseEntity.ok("welcome to park");
	    }
	    
	    
	    @PostMapping("/exit")
	    public ResponseEntity<?> exitPark(@RequestParam String username) {
	       UserRegistration user = (UserRegistration) parkService.findByUsername(username);
           if (user == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	        
	       user.setExitTime(LocalDateTime.now());
	       Duration duration = Duration.between(user.getEntryTime(), user.getExitTime());
	    parkService.saveandupdatetime(user);
	       long hours = duration.toHours();
	    long minutes = duration.minusHours(hours).toMinutes();
	      return ResponseEntity.ok("Duration spent in the park: " + hours + " hours and " + minutes + " minutes");
	    } 
	    
	    
	    @GetMapping("/noofpeoplebetweenage")
	    public ResponseEntity<String> countpeople() 
	    {
	    	int age=25;
	    	int count=parkService.countpeoplebetweenage();
	    	
	    	return ResponseEntity.ok("given age  between peoples are "+count);	
	    }
	    
	    @GetMapping("/countpeoplesbasedonage")
	    public Map<String,Integer> countnumbers()
	    {
	    	return parkService.countpeoples();
	    }
	    
	    
}

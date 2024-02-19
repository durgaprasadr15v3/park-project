package com.example.parkproject.controller;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	    	
	    	 System.out.print(userRegistration.getUsername());
	        // Check if username is already taken
	    	UserRegistration present=new UserRegistration();
	    	 String username=present.getUsername();
//	    	 String comingusername=userRegistration.getUsername();
//		 
	        if (parkService.findByUsername(userRegistration.getUsername()) != null)
	         {
	        	
	            return ResponseEntity.badRequest().body("Username is already taken");
	        }
	        else
	        {
	        	 parkService.saveuserdetails(userRegistration);
	        }
	        // Save the user to the database
	       
	        return ResponseEntity.ok("Registration successful");
	    }
	 
	 
	 
	    @PostMapping("/login")
	    public ResponseEntity<?> loginUser(@RequestParam String userName,@RequestParam String password) {
	   
	    	// Retrieve the user by username
	    	
		// UserRegistration userdata=new UserRegistration();
		 UserRegistration userdata=(UserRegistration) parkService.findByUsername(userName);
	    var user = parkService.findByUsername(userName);
	    userdata.setEntryTime(LocalDateTime.now());
	    parkService.saveandupdatetime(userdata);
	   
	        
	        // Check if user exists and password is correct
	        if (user == null || !((UserRegistration) user).getPassword().equals(password)) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	        }
	        
	        // Generate JWT token (you'll need to implement this)
	       //String token = generateToken(user);
	        
	        // Return token to the client
	        
	        return ResponseEntity.ok("welcome to park");
	    }
	    
	    
	    @PostMapping("/exit")
	    public ResponseEntity<?> exitPark(@RequestParam String username) {
	        // Retrieve the user by username
	    	
	       UserRegistration user = (UserRegistration) parkService.findByUsername(username);
	        
	        // Check if user exists
	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	        
	        // Set exit time and calculate duration
	       user.setExitTime(LocalDateTime.now());
	       Duration duration = Duration.between(user.getEntryTime(), user.getExitTime());
	        
	        // Save the updated user to the database
	    parkService.saveandupdatetime(user);
	        
	        // Return duration spent in the park
	       long hours = duration.toHours();
	    long minutes = duration.minusHours(hours).toMinutes();
	      return ResponseEntity.ok("Duration spent in the park: " + hours + " hours and " + minutes + " minutes");
	    }  
	    
	    
}

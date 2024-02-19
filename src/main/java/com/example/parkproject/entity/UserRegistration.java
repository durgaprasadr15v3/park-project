package com.example.parkproject.entity;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;



@Entity
public class UserRegistration {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(unique = true)
	    @NotNull
	    private String userName;
	    
	    
	    private String password;
	    
	    
	    private String email;
	    
	    @Column(name = "entry_time")
	    private LocalDateTime entryTime;
	    
	    
	    @Column(name = "exit_time")
	    private LocalDateTime exitTime;
	    
	    @PrePersist
	    public void prePersist() {
	        this.entryTime = LocalDateTime.now();
	        // If needed, you can set exitTime here as well
	    }

	    @PreUpdate
	    public void preUpdate() {
	        // If needed, you can update exitTime here as well
	    	this.exitTime = LocalDateTime.now();
	    }


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getUsername() {
			return userName;
		}


		public void setUsername(String username) {
			this.userName = username;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public LocalDateTime getEntryTime() {
			return entryTime;
		}


		public void setEntryTime(LocalDateTime entryTime) {
			this.entryTime = entryTime;
		}


		public LocalDateTime getExitTime() {
			return exitTime;
		}


		public void setExitTime(LocalDateTime exitTime) {
			this.exitTime = exitTime;
		}
	    
	    
}

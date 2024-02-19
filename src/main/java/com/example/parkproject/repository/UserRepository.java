package com.example.parkproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parkproject.entity.UserRegistration;

@Repository
public interface UserRepository extends JpaRepository<UserRegistration, Long> {


	UserRegistration findByUserName(String username);
	
//	List<UserRegistration> findByNameLike(String name);
//	//List<UserRegistration> findByFnameLike(String ch);
//	 @Query("SELECT u FROM user_registration u WHERE u.username = :username")
//	    UserRegistration findByUserName(@Param("username") String username);
//	

}

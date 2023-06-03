package com.lifung.springbootlifung.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lifung.springbootlifung.entity.Users;


@Repository
public interface UserDao extends CrudRepository<Users, Long> {

	Users findById(String id);
	
	Users findByUserName(String userName);
    
}
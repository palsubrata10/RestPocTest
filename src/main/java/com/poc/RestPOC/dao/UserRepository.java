package com.poc.RestPOC.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.RestPOC.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	
}

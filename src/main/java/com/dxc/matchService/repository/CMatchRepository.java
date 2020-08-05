package com.dxc.matchService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dxc.matchService.domain.User;

@Repository
public interface CMatchRepository extends MongoRepository<User,String>{
	public User findByUserName(String userName);
}

package com.openstreetarts.poc1.repository;

import com.openstreetarts.poc1.model.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	UserEntity findByEmail(String email);
}
package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserEntity;

public interface UserRegistrationRepo extends JpaRepository<UserEntity, Integer> {

	public UserEntity findByEmail(String email);
}

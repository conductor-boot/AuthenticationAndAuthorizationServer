package com.my.ansicon.aaaserver.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.ansicon.aaaserver.database.entity.OAuthRole;

public interface OAuthRoleRepository extends JpaRepository<OAuthRole, Integer> {
	
	OAuthRole findByName(String name);

}

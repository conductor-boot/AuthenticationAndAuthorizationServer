package com.my.ansicon.aaaserver.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.my.ansicon.aaaserver.database.entity.OAuthClientDetails;

public interface OAuthClientDetailsRepository extends JpaRepository<OAuthClientDetails, Integer> {

	@Query(value="SELECT * FROM oauth_client_details WHERE client_id = ?1", nativeQuery = true)
	OAuthClientDetails getByClientId(String clientId);
}

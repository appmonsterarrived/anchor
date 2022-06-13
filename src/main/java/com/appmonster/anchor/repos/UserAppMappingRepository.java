package com.appmonster.anchor.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.appmonster.anchor.entity.UserApplicationMapping;

@Repository
public interface UserAppMappingRepository extends JpaRepository<UserApplicationMapping, Long> {
	
	@Query(value = "SELECT APP_ID FROM USER_APP_MAPPING WHERE USER_NAME = ?1" , nativeQuery = true)
	public List<String> fetchUserAppMapping(String userId);

}

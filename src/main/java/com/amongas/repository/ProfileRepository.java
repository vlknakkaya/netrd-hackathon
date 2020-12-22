package com.amongas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amongas.model.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Optional<Profile> findById(long id);
	
	List<Profile> findByName(String name);

	List<Profile> findBySurname(String surname);
	
	Optional<Profile> findByEmail(String email);
	
	Optional<Profile> findByNumber(String number);
	
	List<Profile> findByCreationType(int creationType);
	
}

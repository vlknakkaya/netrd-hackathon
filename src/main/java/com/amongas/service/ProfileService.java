package com.amongas.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amongas.model.entity.Profile;
import com.amongas.repository.ProfileRepository;

@Service
public class ProfileService {

	private ProfileRepository profileRepository;

	@Autowired
	public ProfileService(ProfileRepository profileRepository) {
		super();
		this.profileRepository = profileRepository;
	}

	public Optional<Profile> getProfileById(long id) {
		return profileRepository.findById(id);
	}

	public List<Profile> getProfilesByName(String name) {
		return profileRepository.findByName(name);
	}

	public List<Profile> getProfilesBySurame(String surname) {
		return profileRepository.findBySurname(surname);
	}

	public Optional<Profile> getProfileByEmail(String email) {
		return profileRepository.findByEmail(email);
	}

	public Optional<Profile> getProfileByNumber(String number) {
		return profileRepository.findByNumber(number);
	}

	public List<Profile> getProfilesByCreationType(int creationType) {
		return profileRepository.findByCreationType(creationType);
	}

	public List<Profile> getAllProfiles() {
		return profileRepository.findAll();
	}

	public Profile saveProfile(Profile profile) {
		return profileRepository.save(profile);
	}

	public List<Profile> saveProfiles(List<Profile> profiles) {
		return profileRepository.saveAll(profiles);
	}

	public void removeProfile(Profile profile) {
		profileRepository.delete(profile);
	}

	public void removeProfiles(List<Profile> profiles) {
		profileRepository.deleteAll(profiles);
	}

	public List<Profile> searchProfilesWithNameOrSurname(String name, String surname) {
		Set<Profile> profiles = new LinkedHashSet<>(
				Optional.ofNullable(getProfilesByName(name)).orElseGet(ArrayList::new));
		profiles.addAll(Optional.ofNullable(getProfilesBySurame(surname)).orElseGet(ArrayList::new));

		return new ArrayList<>(profiles);
	}

}

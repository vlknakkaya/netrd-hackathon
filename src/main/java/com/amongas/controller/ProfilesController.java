package com.amongas.controller;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amongas.common.TextToSpeechAlgorithm;
import com.amongas.common.errorhandling.exception.ConditionNameNotFoundException;
import com.amongas.common.errorhandling.exception.NumberNotFoundException;
import com.amongas.common.errorhandling.exception.ProfileIdNotFoundException;
import com.amongas.controller.converter.ConditionConverter;
import com.amongas.controller.converter.PersonConditionConverter;
import com.amongas.controller.converter.ProfileConverter;
import com.amongas.controller.converter.TimeConditionConverter;
import com.amongas.model.dto.CallRequestDTO;
import com.amongas.model.dto.CallResponseDTO;
import com.amongas.model.dto.ConditionDTO;
import com.amongas.model.dto.PersonConditionDTO;
import com.amongas.model.dto.ProfileDTO;
import com.amongas.model.dto.TimeConditionDTO;
import com.amongas.model.entity.Condition;
import com.amongas.model.entity.PersonCondition;
import com.amongas.model.entity.Profile;
import com.amongas.model.entity.TimeCondition;
import com.amongas.service.ConditionService;
import com.amongas.service.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {

	@Autowired
	private ProfileService profileService;
	@Autowired
	private ProfileConverter profileConverter;
	@Autowired
	private ConditionService conditionsService;
	@Autowired
	private ConditionConverter conditionsConverter;
	@Autowired
	private PersonConditionConverter personConditionsConverter;
	@Autowired
	private TimeConditionConverter timeConditionsConverter;

	@GetMapping("/all")
	public List<ProfileDTO> getAllProfiles() {
		return profileConverter.convertToDTOList(profileService.getAllProfiles());
	}

	@GetMapping("/{id}")
	public ProfileDTO getProfileById(@PathVariable long id) throws ProfileIdNotFoundException {
		Profile profile = profileService.getProfileById(id).orElseThrow(() -> new ProfileIdNotFoundException(id));

		return profileConverter.convertToDTO(profile);
	}

	@GetMapping("/{id}/conditions")
	public List<ConditionDTO> getConditionsFromProfileId(@PathVariable long id) throws ProfileIdNotFoundException {
		Profile profile = profileService.getProfileById(id).orElseThrow(() -> new ProfileIdNotFoundException(id));

		return conditionsConverter.convertToDTOList(profile.getConditions());
	}

	@GetMapping("/number/{number}")
	public ProfileDTO getProfileByNumber(@PathVariable String number) throws NumberNotFoundException {
		Profile profile = profileService.getProfileByNumber(number)
				.orElseThrow(() -> new NumberNotFoundException(number));

		return profileConverter.convertToDTO(profile);
	}

	@GetMapping("/search")
	public List<ProfileDTO> getProfilesByNameOrSurname(@RequestParam(required = false) String name,
			@RequestParam(required = false) String surname) {
		return profileConverter.convertToDTOList(profileService.searchProfilesWithNameOrSurname(name, surname));
	}

	@PostMapping("/call")
	public CallResponseDTO call(@RequestBody CallRequestDTO callRequest) throws NumberNotFoundException {
		String to = callRequest.getTo();

		//Profile profile = profileService.getProfileByNumber(to).orElseThrow(() -> new NumberNotFoundException(to));
//
//		Condition activeCondition = null;
//		List<Condition> profileConditions = profile.getConditions();
//		Collections.sort(profileConditions); // to order by priority
//
//		for (Condition conditions : profileConditions) {
//			if (conditions.isStatus() && conditions.checkCondition(callRequest)) {
//				activeCondition = conditions;
//				break;
//			}
//		}
//
//		if (activeCondition != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("audio-file", new File("src/main/resources/sample.wav"));
			body.add("text", "Hello my friend, this is a test message");
			
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			String serverUrl = "http://localhost:5000/file-upload";
			
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<File> response = restTemplate.postForEntity(serverUrl, requestEntity, File.class);
			
			File voiceMessage = response.getBody();
			
			CallResponseDTO callResponse = new CallResponseDTO();
			callResponse.setFrom(callRequest.getTo());
			callResponse.setTo(callRequest.getFrom());
//			callResponse.setMessage(activeCondition.getPersonalMessage());
			callResponse.setMessage(body.get("text").toString());
			callResponse.setAudio(voiceMessage);

			return callResponse;
//		} else {
//			return null;
//		}
	}

	@PostMapping("/create")
	public ResponseEntity<String> createProfile(@RequestBody ProfileDTO profileDTO) {
		Profile profile = profileConverter.convertToEntity(profileDTO);

		try {
			profileService.saveProfile(profile);
		} catch (Exception e) {
			return new ResponseEntity<>("Profile was NOT created: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Profile was created successfully.", HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateProfile(@PathVariable long id, @RequestBody ProfileDTO profileDTO)
			throws ProfileIdNotFoundException {
		Profile profile = profileService.getProfileById(id).orElseThrow(() -> new ProfileIdNotFoundException(id));

		profile.setName(profileDTO.getName());
		profile.setSurname(profileDTO.getSurname());
		profile.setEmail(profileDTO.getEmail());
		profile.setNumber(profileDTO.getNumber());
		profile.setSample(profileDTO.getSample());
		profile.setCreationType(profileDTO.getCreationType());

		try {
			profileService.saveProfile(profile);
		} catch (Exception e) {
			return new ResponseEntity<>("Profile was NOT updated: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Profile was updated successfully.", HttpStatus.OK);
	}

	@PostMapping("/{id}/conditions/person")
	public ResponseEntity<String> createPersonCondition(@PathVariable long id,
			@RequestBody PersonConditionDTO personConditionDTO) throws ProfileIdNotFoundException {
		Profile profile = profileService.getProfileById(id).orElseThrow(() -> new ProfileIdNotFoundException(id));

		try {
			PersonCondition personCondition = personConditionsConverter.convertToEntity(personConditionDTO);
			profile.addCondition(conditionsService.saveCondition(personCondition));
			profileService.saveProfile(profile);
		} catch (Exception e) {
			return new ResponseEntity<>("Condition was NOT added: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Condition was added successfully.", HttpStatus.OK);
	}

	@PostMapping("/{id}/conditions/time")
	public ResponseEntity<String> createTimeCondition(@PathVariable long id,
			@RequestBody TimeConditionDTO timeConditionDTO) throws ProfileIdNotFoundException {
		Profile profile = profileService.getProfileById(id).orElseThrow(() -> new ProfileIdNotFoundException(id));

		try {
			TimeCondition timeCondition = timeConditionsConverter.convertToEntity(timeConditionDTO);
			profile.addCondition(conditionsService.saveCondition(timeCondition));
			profileService.saveProfile(profile);
		} catch (Exception e) {
			return new ResponseEntity<>("Condition was NOT added: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Condition was added successfully.", HttpStatus.OK);
	}

	@PutMapping("/{id}/conditions/person/{name}")
	public ResponseEntity<String> updatePersonCondition(@PathVariable long id, @PathVariable String name,
			@RequestBody PersonConditionDTO personConditionDTO) throws ConditionNameNotFoundException {
		PersonCondition condition = conditionsService.getPersonConditionByName(name)
				.orElseThrow(() -> new ConditionNameNotFoundException(name));

		condition.setName(personConditionDTO.getName());
		condition.setType(personConditionDTO.getType());
		condition.setStatus(personConditionDTO.isActive());
		condition.setPriority(personConditionDTO.getPriority());
		condition.setPersonalMessage(personConditionDTO.getMessage());
		condition.setFromPerson(personConditionDTO.getFrom());

		try {
			conditionsService.saveCondition(condition);
		} catch (Exception e) {
			return new ResponseEntity<>("Condition was NOT updated: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Condition was updated successfully.", HttpStatus.OK);
	}

	@PutMapping("/{id}/conditions/time/{name}")
	public ResponseEntity<String> updateTimeCondition(@PathVariable long id, @PathVariable String name,
			@RequestBody TimeConditionDTO timeConditionDTO) throws ConditionNameNotFoundException {
		TimeCondition condition = conditionsService.getTimeConditionByName(name)
				.orElseThrow(() -> new ConditionNameNotFoundException(name));

		condition.setName(timeConditionDTO.getName());
		condition.setType(timeConditionDTO.getType());
		condition.setStatus(timeConditionDTO.isActive());
		condition.setPriority(timeConditionDTO.getPriority());
		condition.setPersonalMessage(timeConditionDTO.getMessage());
		condition.setStart(timeConditionDTO.getStart());
		condition.setEnd(timeConditionDTO.getEnd());

		try {
			conditionsService.saveCondition(condition);
		} catch (Exception e) {
			return new ResponseEntity<>("Condition was NOT updated: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Condition was updated successfully.", HttpStatus.OK);
	}

}

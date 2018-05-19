package de.dorianweidler.boscallserver.api;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.dorianweidler.boscallserver.api.dto.RetrieveUnitsRequest;
import de.dorianweidler.boscallserver.api.dto.RegistrationRequest;
import de.dorianweidler.boscallserver.api.dto.RegistrationResponse;
import de.dorianweidler.boscallserver.api.dto.UnregistrationRequest;
import de.dorianweidler.boscallserver.dto.Unit;
import de.dorianweidler.boscallserver.dto.User;
import de.dorianweidler.boscallserver.repositories.UnitRepository;
import de.dorianweidler.boscallserver.repositories.UserRepository;
import de.dorianweidler.boscallserver.util.Util;

@RestController
@RequestMapping(path = "api")
public class RegistrationEndpoint {

	final int API_KEY_LENGTH = 128;

	@Autowired
	UnitRepository unitRepository;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(path = "registration", method = RequestMethod.POST)
	public ResponseEntity<RegistrationResponse> register(
			@RequestBody(required = false) RegistrationRequest registrationRequest) {
		if (registrationRequest == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Unit unitDto = unitRepository.findByIdAndSecret(registrationRequest.getUnitId(),
				registrationRequest.getSecret());

		if (unitDto != null) {
			User userDto = userRepository.findByToken(registrationRequest.getToken());

			if (userDto == null) {
				// everything correct
				String apiKey = Util.generateRandomString(API_KEY_LENGTH);
				userDto = new User();
				userDto.setApiKey(apiKey);
			}
			
			if (userDto.getUnits() == null) {
				userDto.setUnits(new ArrayList<>());
			} else {
				if(userDto.getUnits().indexOf(unitDto) >= 0) {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
			userDto.getUnits().add(unitDto);
			userDto.setToken(registrationRequest.getToken());
			userDto.setName(registrationRequest.getUserName());
			userDto = userRepository.save(userDto);

			RegistrationResponse responseBody = new RegistrationResponse();
			responseBody.setApiKey(userDto.getApiKey());
			responseBody.setUserId(userDto.getId());
			responseBody.setUnitName(unitDto.getName());
			ResponseEntity<RegistrationResponse> response = new ResponseEntity<>(responseBody, HttpStatus.CREATED);
			return response;
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(path = "registration", method = RequestMethod.DELETE)
	public ResponseEntity<RegistrationResponse> unregister(
			@RequestBody(required = false) UnregistrationRequest unregistrationRequest) {
		if (unregistrationRequest == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Unit unitDto = unitRepository.findById(unregistrationRequest.getUnitId());
		User userDto = userRepository.findByIdAndApiKey(unregistrationRequest.getUserId(),
				unregistrationRequest.getApiKey());

		if (userDto != null && unitDto != null) {
			int pos = userDto.getUnits().indexOf(unitDto);
			if (pos >= 0) {
				userDto.getUnits().remove(pos);
				userRepository.save(userDto);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping(path = "units", method = RequestMethod.POST)
	public ResponseEntity<List<BigInteger>> getUnits(@RequestBody(required = false) RetrieveUnitsRequest request) {
		if(request == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User userDto = userRepository.findByIdAndApiKey(request.getUserId(), request.getApiKey());
		if(userDto == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<BigInteger>>(unitRepository.findUnitIdsByUserId(request.getUserId()), HttpStatus.OK);
	}

}

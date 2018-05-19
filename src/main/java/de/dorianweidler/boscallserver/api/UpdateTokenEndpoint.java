package de.dorianweidler.boscallserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.dorianweidler.boscallserver.api.dto.UpdateTokenRequest;
import de.dorianweidler.boscallserver.dto.User;
import de.dorianweidler.boscallserver.repositories.UnitRepository;
import de.dorianweidler.boscallserver.repositories.UserRepository;

@RestController
@RequestMapping(path = "api")
public class UpdateTokenEndpoint {

	final int API_KEY_LENGTH = 128;

	@Autowired
	UnitRepository unitRepository;
	
	@Autowired
	UserRepository userRepository;

	@RequestMapping(path = "updateToken", method = RequestMethod.POST)
	public ResponseEntity<Object> updateToken(@RequestBody(required = false) UpdateTokenRequest request) {
		if (request == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User userDto = userRepository.findByIdAndApiKey(request.getUserId(), request.getApiKey());
		userDto.setToken(request.getToken());
		userDto.setName(request.getUserName());
		userRepository.save(userDto);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

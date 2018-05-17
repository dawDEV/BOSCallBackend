package de.dorianweidler.boscallserver.api;

import org.springframework.beans.factory.annotation.Autowired;
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
	public boolean updateToken(@RequestBody(required = false) UpdateTokenRequest request) {
		if (request == null) {
			return false;
		}

		User userDto = userRepository.findByIdAndApiKey(request.getUserId(), request.getApiKey());

		if (userDto != null) {
			// everything correct
			userDto.setToken(request.getToken());
			userRepository.save(userDto);
			return true;
		}

		return false;
	}

}

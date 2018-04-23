package de.dorianweidler.boscallserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.dorianweidler.boscallserver.api.dto.RegistrationRequest;
import de.dorianweidler.boscallserver.dto.Unit;
import de.dorianweidler.boscallserver.repositories.UnitRepository;

@RestController
@RequestMapping(path = "api")
public class RegistrationEndpoint {
	
	@Autowired
	UnitRepository unitRepository;

	@RequestMapping(path = "register", method = RequestMethod.POST)
	public boolean register(@RequestBody(required = false) RegistrationRequest registrationRequest) {
		if(registrationRequest == null) {
			return false;
		}
		System.err.println("======= REQUEST =======");
		System.err.println(registrationRequest);
		
		System.err.println("======= Found Element =======");
		Unit u = unitRepository.findById(registrationRequest.getUnitId()).orElse(null);
		
		System.err.println(u);
		
		
		return false;
	}
	
}

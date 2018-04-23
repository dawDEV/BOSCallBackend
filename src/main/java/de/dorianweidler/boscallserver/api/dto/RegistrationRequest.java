package de.dorianweidler.boscallserver.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegistrationRequest {

	private long unitId;
	private String secret;
	private String apiCode;
	
}

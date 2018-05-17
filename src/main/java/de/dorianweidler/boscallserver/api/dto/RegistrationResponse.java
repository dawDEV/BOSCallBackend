package de.dorianweidler.boscallserver.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegistrationResponse {

	private long userId;
	private String apiKey;
	private String unitName;
	
}

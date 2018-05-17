package de.dorianweidler.boscallserver.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UnregistrationRequest {

	private long unitId;
	private String apiKey;
	private long userId;
	
}

package de.dorianweidler.boscallserver.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateTokenRequest {

	private int userId;
	private String apiKey;
	private String token;
	private String userName;
	
}

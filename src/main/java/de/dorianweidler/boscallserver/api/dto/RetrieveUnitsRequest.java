package de.dorianweidler.boscallserver.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RetrieveUnitsRequest {

	private int userId;
	private String apiKey;
	
}

package com.kunal.advocateConnect.responseModel;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class JwtResponse {

	@NonNull
	private String token;
	
	private String type = "Bearer";
	
	@NonNull
	private Long id;
	
	@NonNull
	private String username;

	@NonNull
	private String email;
	
	@NonNull
	private Set<String> roles;
}

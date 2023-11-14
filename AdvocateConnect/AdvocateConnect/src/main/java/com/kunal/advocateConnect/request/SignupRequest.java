package com.kunal.advocateConnect.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {
	@NotNull
	private Long id;
	@NotNull
	@Size(min = 3, max = 20)
	private String username;

	@NotNull
	@Size(max=50)
	@Email
	private String email;

	@NotNull
	@Size(min = 4, max = 40)
	private String password;
	
	private Set<String> role;
	
}

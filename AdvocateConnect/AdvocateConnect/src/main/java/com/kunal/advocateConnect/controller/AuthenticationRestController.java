package com.kunal.advocateConnect.controller;


import com.kunal.advocateConnect.entity.Role;
import com.kunal.advocateConnect.entity.User;
import com.kunal.advocateConnect.entity.UserDetailsImpl;
import com.kunal.advocateConnect.repo.RoleRepository;
import com.kunal.advocateConnect.repo.UserRepository;
import com.kunal.advocateConnect.request.LoginRequest;
import com.kunal.advocateConnect.request.SignupRequest;
import com.kunal.advocateConnect.responseModel.JwtResponse;
import com.kunal.advocateConnect.responseModel.MessageResponse;
import com.kunal.advocateConnect.util.JwtUtils;
import com.kunal.advocateConnect.util.RolesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RolesUtils rolesUtils;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RoleRepository roleRepository;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		UserDetailsImpl userDetails= (UserDetailsImpl)authentication.getPrincipal();
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				userDetails.getAuthorities()
						.stream()
						.map(auth->auth.getAuthority())
						.collect(Collectors.toSet()) //Set<String>
				)
		);
	}

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest) {
		if(userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error  : Username already exist"));
		}
		if(userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error  : EmailId already exist"));
		}
		User user = new User(
				signupRequest.getId(),
				signupRequest.getUsername(),
				signupRequest.getEmail(),
				encoder.encode(signupRequest.getPassword())
		);
		Set<String> usrRoles = signupRequest.getRole();
		Set<Role> dbRoles = new HashSet<>();
		rolesUtils.mapRoles(usrRoles, dbRoles);
		user.setRoles(dbRoles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User Created Successfully!"));
	}

	@PostMapping("/addRole")
	public String addRole(@Valid @RequestBody Role role) {
		roleRepository.save(role);
        return  "Role has been added";
	}

}

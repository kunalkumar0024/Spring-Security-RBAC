package com.kunal.advocateConnect.controller;

import com.kunal.advocateConnect.entity.Role;
import com.kunal.advocateConnect.entity.User;
import com.kunal.advocateConnect.repo.UserRepository;
import com.kunal.advocateConnect.request.RoleRequest;
import com.kunal.advocateConnect.util.AppUtils;
import com.kunal.advocateConnect.util.RolesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/home")
public class HomeRestController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RolesUtils rolesUtils;
	@GetMapping("/all")
	public String allowAll() {
		return "Welcome to HomePage!";
	}
	
	@GetMapping("/user")
	public String userData() {
		return "User Data!";
	}
	
	@GetMapping("/mod")
	public String moderatorData() {
		return "Moderator Data!";
	}
	
	@GetMapping("/admin")
	public String adminData() {
		return "Admin Data!";
	}

	@PutMapping("/assignRole")
	public String assignRole(@Valid @RequestBody RoleRequest signupRequest) {
		Set<String> usrRoles = signupRequest.getRole();
		Set<Role> dbRoles = new HashSet<>();
		User user = userRepository.findByUsername(signupRequest.getUsername())
				.orElseThrow(() -> new RuntimeException("Data not found for given user"));
		rolesUtils.mapRoles(usrRoles, dbRoles);
		user.setRoles(dbRoles);
		String role = null;
		for (String value : usrRoles) {
			role = value;
		}
		int roleId = role.equals("ADMIN") ? AppUtils.ADMIN_ROLE_ID : role.equals("MODERATOR") ? AppUtils.MODERATOR_ROLE_ID : role.equals("USER") ? AppUtils.USER_ROLE_ID : null;
		userRepository.updateUserRole(user.getId(), roleId);
		return "User data has been updated";
	}
}

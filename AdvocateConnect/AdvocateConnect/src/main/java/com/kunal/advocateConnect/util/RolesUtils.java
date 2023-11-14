package com.kunal.advocateConnect.util;

import com.kunal.advocateConnect.entity.ERole;
import com.kunal.advocateConnect.entity.Role;
import com.kunal.advocateConnect.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RolesUtils {

	@Autowired
	private RoleRepository repository;

	public void mapRoles(Set<String> userRoles, Set<Role> dbRoles) {

		for (String value : userRoles) {

			if (value.equals("ADMIN")) {
				Role usr = repository.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
				dbRoles.add(usr);
			} else if (value.equals("MODERATOR")) {
				Role usr = repository.findByName(ERole.ROLE_MODERATOR)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
				dbRoles.add(usr);
			} else {
				Role usr = repository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
				dbRoles.add(usr);
			}
		}
	}
}

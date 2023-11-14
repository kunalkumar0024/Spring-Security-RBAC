package com.kunal.advocateConnect.repo;


import com.kunal.advocateConnect.entity.ERole;
import com.kunal.advocateConnect.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(ERole name);
}

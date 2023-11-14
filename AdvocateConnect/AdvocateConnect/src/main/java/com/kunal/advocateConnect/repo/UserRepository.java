package com.kunal.advocateConnect.repo;

import com.kunal.advocateConnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	@Transactional
	@Modifying
	@Query(value = "update users_roles_tab set role_id=:roles where user_id=:userId", nativeQuery = true)
	void updateUserRole(@Param("userId") Long userId, @Param("roles") Integer roles);
}

package com.kunal.advocateConnect.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@RequiredArgsConstructor
@Entity
@Table(
		name="users_tab",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email")
		})
public class User {
	@Id
	@Column(name = "id")
	private Long id;
	@Size(max=20)
	@NonNull
	@Column(name = "username")
	private String username;
	@Size(max=50)
	@Email
	@NonNull
	@Column(name = "email")
	private String email;
	@NotBlank
	@Size(max=120)
	@NonNull
	@Column(name = "password")
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="users_roles_tab",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id")
			)
	@Column(name = "roles")
	private Set<Role> roles;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User(Long id, @NonNull String username, @NonNull String email, @NonNull String password, Set<Role> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public User(Long id, @NonNull String username, @NonNull String email, @NonNull String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User() {
	}

}

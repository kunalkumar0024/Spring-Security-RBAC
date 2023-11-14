package com.kunal.advocateConnect.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles_tab")
public class Role {
	@Id
	private Integer id;
	@Enumerated(EnumType.STRING)
	private ERole name;
}

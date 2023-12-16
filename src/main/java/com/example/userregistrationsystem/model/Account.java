package com.example.userregistrationsystem.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Data
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@NotNull
	private String user_first_name;

	@NotEmpty
	@NotNull
	private String user_last_name;

	@Column(unique = true)
	@Email
	private String user_email;

	@NotNull
	@NotEmpty
	@Min(value=8, message="Minimum size is 8.")
	private String user_password;

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
		name="account_roles",
		joinColumns={@JoinColumn(name="account_id", referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")}
	)
	private List<Role> roles = new ArrayList<>();
}

package com.example.userregistrationsystem.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Data
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@NotNull
	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<Account> accounts = new ArrayList<>();
	
}

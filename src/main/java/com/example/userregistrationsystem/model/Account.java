package com.example.userregistrationsystem.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Data
@Table(
	uniqueConstraints=@UniqueConstraint(
		name="Unique_Email",
		columnNames={"user_email"}
	)
)
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@NotNull
	private String userFirstName;

	@NotEmpty
	@NotNull
	private String userLastName;

	@Email
	private String userEmail;

	@NotNull
	@NotEmpty
	@Size(min=8, message="Minimum size is 8.")
	private String userPassword;

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
		name="account_roles",
		joinColumns={
			@JoinColumn(
				name="account_id",
				referencedColumnName="id",
				foreignKey=@ForeignKey(name="FK_AccountId_Id", value=ConstraintMode.CONSTRAINT)
				)},
		inverseJoinColumns={
			@JoinColumn(
				name="role_id",
				referencedColumnName="id",
				foreignKey=@ForeignKey(name="FK_RoleId_Id", value=ConstraintMode.CONSTRAINT)
			)
		}
	)
	private List<Role> roles = new ArrayList<>();
}

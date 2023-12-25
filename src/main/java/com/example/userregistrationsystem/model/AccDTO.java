package com.example.userregistrationsystem.model;

import lombok.*;
import jakarta.validation.constraints.*;

@NoArgsConstructor
@Data
public class AccDTO {

	@NotEmpty
	private String userFirstName;

	@NotEmpty
	private String userLastName;

	@Email
	@NotEmpty(message = "Email should not be empty")
	private String userEmail;

	@NotEmpty
	@Min(value=8, message="Minimum size is 8.")
	private String userPassword;
}

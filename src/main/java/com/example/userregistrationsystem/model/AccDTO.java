package com.example.userregistrationsystem.model;

import lombok.*;
import jakarta.validation.constraints.*;

@NoArgsConstructor
@Data
public class AccDTO {

	@NotEmpty
	private String user_first_name;

	@NotEmpty
	private String user_last_name;

	@Email
	@NotEmpty(message = "Email should not be empty")
	private String user_email;

	@NotEmpty
	@Min(value=8, message="Minimum size is 8.")
	private String user_password;
}

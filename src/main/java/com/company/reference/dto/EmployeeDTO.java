package com.company.reference.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	@NotBlank(message = "First name cannot be empty or null")
	@Pattern(regexp = "^[A-Z][a-z]{1,29}$", message = "First name must have 2 - 30 characters and first letter in capital")
	private String firstName;

	@Pattern(regexp = "^[A-Z][a-z]{1,29}$", message = "Middle name must have 2 - 30 characters and first letter in capital")
	private String middleName;

	@NotBlank(message = "Last name cannot be empty or null")
	@Pattern(regexp = "^[A-Z][a-z]{1,29}$", message = "Last name must have 2 - 30 characters and first letter in capital")
	private String lastName;

	@NotBlank(message = "Email cannot be empty or null")
	@Email(message = "Email is not valid")
	private String email;

	@NotBlank(message = "Phone cannot be empty or null")
	@Pattern(regexp = "^\\d{10}$", message = "Phone number is not valid")
	private String phone;

	@NotNull(message = "Age cannot be null")
	@Min(value = 10, message = "Age must be greater or equal to 10")
	@Max(value = 80, message = "Age cannot be greater than 80")
	private Long age;

}

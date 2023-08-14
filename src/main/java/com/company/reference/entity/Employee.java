package com.company.reference.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "reference_employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "first_name", length = 30)
	private String firstName;

	@Column(name = "middle_name", length = 30)
	private String middleName;

	@Column(name = "last_name", length = 30)
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "phone", unique = true, length = 10)
	private String phone;

	@Column(name = "age")
	private Long age;

}

package com.company.reference;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.company.reference.entity.Employee;
import com.company.reference.repository.EmployeeRepository;
import com.github.javafaker.Faker;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ReferenceSpringBootProjectApplication {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReferenceSpringBootProjectApplication.class, args);
		System.out.println("----- BACKEND SERVER RUNNING SUCCESSFULLY -----");
	}

	@PostConstruct
	public void loadFakeData() {
		Faker faker = new Faker();
		List<Employee> employees = IntStream.rangeClosed(1, 100).mapToObj(employee -> {
			Long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			String phone = number.toString();
			return Employee.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName())
					.email(faker.internet().emailAddress()).phone(phone).age(21L).build();
		}).toList();
		employeeRepository.saveAll(employees);
	}

}

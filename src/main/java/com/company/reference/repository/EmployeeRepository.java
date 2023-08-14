package com.company.reference.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.reference.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);

	Optional<Employee> findByPhone(String phone);

	List<Employee> findByAgeBetween(Long start, Long end);

	List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

}

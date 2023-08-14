package com.company.reference.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.company.reference.dto.EmployeeDTO;
import com.company.reference.entity.Employee;

@Service
public interface EmployeeService {

	Employee saveEmployee(EmployeeDTO employeeDTO);

	List<Employee> getAllEmployee();

	Page<Employee> getAllEmployee(Integer page, Integer size);

	List<Employee> getAllEmployeeByAgeBetween(Long start, Long end);

	Employee getEmployeeById(Long employeeId);

	Employee getEmployeeByPhone(String phone);

	Employee updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO);

	String deleteEmployeeById(Long employeeId);

	String deleteAllEmployee();

}

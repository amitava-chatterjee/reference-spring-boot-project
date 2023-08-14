package com.company.reference.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.reference.dto.EmployeeDTO;
import com.company.reference.entity.Employee;
import com.company.reference.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employee")
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
		Employee employee = employeeService.saveEmployee(employeeDTO);
		LOGGER.info("A employee has been saved: {}", employee);
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}

	@GetMapping("/fetch-all-by-age")
	public ResponseEntity<List<Employee>> getAllEmployeeByAgeBetween(@RequestParam(name = "lb") Long start,
			@RequestParam(name = "ub") Long end) {
		List<Employee> employees = employeeService.getAllEmployeeByAgeBetween(start, end);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/fetch-all")
	public ResponseEntity<?> getAllEmployee(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		if (page == null || size == null) {
			List<Employee> employees = employeeService.getAllEmployee();
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} else {
			Page<Employee> employees = employeeService.getAllEmployee(page, size);
			return new ResponseEntity<>(employees, HttpStatus.OK);
		}
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@GetMapping("/fetch-by-phone/{phone}")
	public ResponseEntity<Employee> getEmployeeByPhone(@PathVariable String phone) {
		Employee employee = employeeService.getEmployeeByPhone(phone);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") Long employeeId,
			@RequestBody @Valid EmployeeDTO employeeDTO) {
		Employee employee = employeeService.updateEmployeeById(employeeId, employeeDTO);
		LOGGER.info("A employee has been updated: {}", employee);
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long employeeId) {
		String message = employeeService.deleteEmployeeById(employeeId);
		LOGGER.info("A employee with id: {} has been deleted", employeeId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/delete-all")
	public ResponseEntity<String> deleteAllEmployee() {
		String message = employeeService.deleteAllEmployee();
		LOGGER.info("All employee have been deleted");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}

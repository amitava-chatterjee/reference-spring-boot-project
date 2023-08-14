package com.company.reference.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.company.reference.dto.EmployeeDTO;
import com.company.reference.entity.Employee;
import com.company.reference.exception.EmployeeNotFoundException;
import com.company.reference.mapper.EmployeeMapper;
import com.company.reference.repository.EmployeeRepository;
import com.company.reference.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(EmployeeDTO employeeDTO) {
		Employee employee = employeeMapper.employeeFromEmployeeDTO(employeeDTO);
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> employees = employeeRepository.findAll();

		if (employees.size() == 0) {
			throw new EmployeeNotFoundException("No employee found");
		}

		return employees;
	}

	@Override
	public Page<Employee> getAllEmployee(Integer page, Integer size) {
		getAllEmployee();
		Pageable pageable = PageRequest.of(page, size);
		return employeeRepository.findAll(pageable);
	}

	@Override
	public List<Employee> getAllEmployeeByAgeBetween(Long start, Long end) {
		List<Employee> employees = employeeRepository.findByAgeBetween(start, end);

		if (employees.size() == 0) {
			throw new EmployeeNotFoundException("No employee found between age of " + start + " and " + end);
		}

		return employees;
	}

	@Override
	public Employee getEmployeeById(Long employeeId) {
		return employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with id: " + employeeId + " not found"));
	}

	@Override
	public Employee getEmployeeByPhone(String phone) {
		return employeeRepository.findByPhone(phone)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with phone: " + phone + " not found"));
	}

	@Override
	public Employee updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
		Employee oldEmployee = getEmployeeById(employeeId);
		oldEmployee.setFirstName(employeeDTO.getFirstName());
		oldEmployee.setMiddleName(employeeDTO.getMiddleName());
		oldEmployee.setLastName(employeeDTO.getLastName());
		oldEmployee.setEmail(employeeDTO.getEmail());
		oldEmployee.setPhone(employeeDTO.getPhone());
		oldEmployee.setAge(employeeDTO.getAge());
		return employeeRepository.save(oldEmployee);
	}

	@Override
	public String deleteEmployeeById(Long employeeId) {
		Employee employee = getEmployeeById(employeeId);
		employeeRepository.delete(employee);
		return "Employee with id: " + employeeId + " deleted successfully";
	}

	@Override
	public String deleteAllEmployee() {
		getAllEmployee();
		employeeRepository.deleteAll();
		return "All employees are deleted successfully";
	}

}

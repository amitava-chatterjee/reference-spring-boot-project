package com.company.reference.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.company.reference.dto.EmployeeDTO;
import com.company.reference.entity.Employee;

@Configuration
public class EmployeeMapper {

	@Autowired
	private ModelMapper modelMapper;

	public Employee employeeFromEmployeeDTO(EmployeeDTO employeeDTO) {
		return modelMapper.map(employeeDTO, Employee.class);
	}

	public EmployeeDTO employeeDTOFromEmployee(Employee employee) {
		return modelMapper.map(employee, EmployeeDTO.class);
	}

}

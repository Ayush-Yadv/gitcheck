package com.example.demo.client;

import com.example.demo.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeClient {

    public List<EmployeeDto> getAllEmployee();

    public EmployeeDto getEmployeeById(Long employeeid);


    public EmployeeDto createEmployee(EmployeeDto employeeDto);

}

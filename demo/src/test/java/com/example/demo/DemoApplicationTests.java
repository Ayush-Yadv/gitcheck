package com.example.demo;

import com.example.demo.client.EmployeClient;
import com.example.demo.config.RestClientConfig;
import com.example.demo.dto.EmployeeDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoApplicationTests {


	@Test
	void contextLoads() {
	}



	@Autowired

	EmployeClient employeClient;

	@Test
    @Order(3)
	void getAllEmployee(){
		List<EmployeeDto> EmployeeDtolist=employeClient.getAllEmployee();
		System.out.println(EmployeeDtolist);
	}

    @Test
    @Order(2)
     void getEmployeeByIdTest(){
        EmployeeDto employeeDtoList=employeClient.getEmployeeById(1l);
        System.out.println(employeeDtoList);
    }


    @Test
    @Order(1)
    void createEmployeeTest(){
        EmployeeDto employeeDto;
        employeeDto = new EmployeeDto(null,12,"Ayush","Ayush@gmail.com",
                LocalDate.of(2020,12,22),true);
        EmployeeDto savedEmployeeDto= employeClient.createEmployee(employeeDto);
        System.out.println(savedEmployeeDto);
    }

}

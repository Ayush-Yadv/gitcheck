package com.example.demo.client.impl;

import com.example.demo.advice.ApiResponse;
import com.example.demo.client.EmployeClient;
import com.example.demo.config.RestClientConfig;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeImpl implements EmployeClient {


    private final RestClient restClient;


    @Override
    public List<EmployeeDto> getAllEmployee() {
        try {
            ApiResponse<List<EmployeeDto>> employeeDto = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDto.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeid) {
        try {
            ApiResponse<EmployeeDto> employeeDtoApiResponse = restClient.get()
                    .uri("employees/{employeeid}", employeeid)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDtoApiResponse.getData();
        }
        catch (Exception e){
            throw  new RuntimeException(e);
        }

    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        try{
            ApiResponse<EmployeeDto> employeeDtoResponse= restClient.post()
                    .uri("employees")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req ,res)->{
                        System.out.println(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could /ot create the employee ");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDtoResponse.getData();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

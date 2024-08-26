package com.codingshuttle.zaid.prod_ready_features.prod_ready_features.clients.Impl;

import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.advice.ApiResponse;
import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.clients.EmployeeClientService;
import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientServiceImpl implements EmployeeClientService {

    private final RestClient restClient;


    @Override
    public List<EmployeeDTO> getAllEmployee() {
      try{
          ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                  .uri("employees")
                  .retrieve()
                  .body(new ParameterizedTypeReference<>() {

                  });
          return employeeDTOList.getData();
      }catch (Exception e){
          throw new RuntimeException();
      }
      }

    public EmployeeDTO getEmployeeById(Long employeeId) {
        try{
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {

                    });
            return employeeResponse.getData();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }


    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try{
           ResponseEntity< ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,resp)->{

                            System.out.println(new String(resp.getBody().readAllBytes()));

                            throw new ResourceNotFoundException("Could not create the employee");
                    })
                   //.body(new ParameterizedTypeReference<>() {

                    //})
                   .toEntity(new ParameterizedTypeReference<>() {

                   });
            return employeeDTOApiResponse.getBody().getData();
           // return employeeDTOApiResponse.getData();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}

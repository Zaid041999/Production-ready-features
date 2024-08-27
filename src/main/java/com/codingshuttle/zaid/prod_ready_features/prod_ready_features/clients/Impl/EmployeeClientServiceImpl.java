package com.codingshuttle.zaid.prod_ready_features.prod_ready_features.clients.Impl;

import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.advice.ApiResponse;
import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.clients.EmployeeClientService;
import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger log = LoggerFactory.getLogger(EmployeeClientServiceImpl.class);

    private final RestClient restClient;


    @Override
    public List<EmployeeDTO> getAllEmployee() {
        log.trace("Trying to retrieve all employee in getAllEmployees");
      try{
          log.info("Attempting to call the RestClient method in getAllEmployees");
          ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                  .uri("employees")
                  .retrieve()
                  .onStatus(HttpStatusCode::is4xxClientError,(req,resp)->{
                      log.error(new String(resp.getBody().readAllBytes()));
                      throw new ResourceNotFoundException("Could not find the employee in getAllEmployees");
                  })
                  .body(new ParameterizedTypeReference<>() {
                  });
          log.debug("Successfully retrieve the employee in getAllEmployees");
          log.trace("Retrieved employee list in getAllEmployees {}",employeeDTOList.getData());
          return employeeDTOList.getData();
      }catch (Exception e){
          log.error("Exception occur in getAllEmployees " ,e);
          throw new RuntimeException();
      }
      }

    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Trying to get employee by id in getEmployeeById {}",employeeId);
        try{
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,resp)->{
                        log.error(new String(resp.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not get the employee by id in getEmployeeById");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeResponse.getData();
        }catch (Exception e){
            log.error("Exception occur in getEmployeeById " ,e);
            throw new RuntimeException();
        }
    }


    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create employee with information {}",employeeDTO);
        try{
           ResponseEntity< ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,resp)->{
                            log.debug("4xx client error occur during create new employee ");
                            log.error(new String(resp.getBody().readAllBytes()));
                            throw new ResourceNotFoundException("Could not create the employee");
                    })
                   //.body(new ParameterizedTypeReference<>() {

                    //})
                   .toEntity(new ParameterizedTypeReference<>() {
                   });
            log.trace("Successfully created a new employee {} ",employeeDTOApiResponse.getBody());
            return employeeDTOApiResponse.getBody().getData();
           // return employeeDTOApiResponse.getData();
        }catch (Exception e){
            log.error("Exception occur in createNewEmployee " ,e);
            throw new RuntimeException();
        }
    }
}

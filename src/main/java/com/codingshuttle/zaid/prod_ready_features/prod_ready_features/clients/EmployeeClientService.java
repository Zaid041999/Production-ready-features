package com.codingshuttle.zaid.prod_ready_features.prod_ready_features.clients;

import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClientService {
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getEmployeeById(Long employeeId);
    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);

}

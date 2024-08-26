package com.codingshuttle.zaid.prod_ready_features.prod_ready_features;

import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.clients.EmployeeClientService;
import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesApplicationTests {

	@Autowired
	private EmployeeClientService employeeClient;

	@Test
	@Order(3)
	void getAllEmployeesTest(){
		List<EmployeeDTO> employeeDTOList =employeeClient.getAllEmployee();
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void getEmployeeByIdTest(){
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}

	@Test
	@Order(1)
	void createNewEmployeeByIdTest(){
		EmployeeDTO employeeDTO = new EmployeeDTO(null,"Zaid","zaid@gmail.com",5000.0
				,24,"USER", LocalDate.of(2020,12,1),true);
		EmployeeDTO savedEmployeeDTO =employeeClient.createNewEmployee(employeeDTO);
		System.out.println(savedEmployeeDTO);
	}


}

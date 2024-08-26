package com.codingshuttle.zaid.prod_ready_features.prod_ready_features.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
    private Long id;

//    @NotBlank(message ="Name of the employee cannot be blank")
//    @Size(min = 3,max=10,message = "Number of character should be in a range:[3,10]")
    private String name;

//    @Email(message = "Email should be a valid email")
  /* // @Pattern(regexp = "^[(a-z0-9-\\_\\.!\\a-z0-9\\D)]+@[(a-zA-Z)]+\\.[(a-zA-Z)]{2,3}$")*/
    private String email;

//    @NotNull(message = "Salary of Employee should be not null")
//    @Positive(message = "Salary of Employee should be positive")
//    @Digits(integer = 6,fraction = 2,message = "The salary can be in the form of xxxxxx.yy")
//    @DecimalMax(value = "100000.99",message = "The salary can be maximum 1000000.99")
//    @DecimalMin(value = "100.22",message = "The salary can be minimum 100.22")
    private Double salary;

//    @NotNull(message = "Age of the employee cannot be blank")
//    @Max( value = 80 ,message = "Age cannot be greater than 80")
//    @Min(value=18, message = "Age cannot be smaller than 18")
    private Integer age;

//    @NotBlank(message ="Role of the employee cannot be blank")
//  //@Pattern(regexp ="^(ADMIN|USER)$",message = "Role of employee can be user or admin")
//    @EmployeeRoleValidation
    private String role; //ADMIN or USER

  //  @PastOrPresent(message = "DateOfJoining field cannot be in the future")
    private LocalDate dateOfJoining;

//    @AssertTrue(message = "Employee should be active")
//    @JsonProperty("isActive")
    private Boolean isActive;


}

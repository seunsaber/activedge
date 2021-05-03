package com.activedge.assessment.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class EmployeeRequest implements Serializable {
    @NotBlank(message = "required")
    private String employeeId;
    @NotBlank(message = "required")
    private String firstName;
    @NotBlank(message = "required")
    private String lastName;
    @Min(value = 0, message = "input a valid integer")
    @Max(value = 100, message = "This person is too old to be an employee.")
    private Integer age;
    @NotBlank(message = "required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String joinDate;

    public EmployeeRequest(@NotBlank(message = "required") String employeeId, @NotBlank(message = "required") String firstName, @NotBlank(message = "required") String lastName, @Min(value = 0, message = "input a valid integer") @Max(value = 100, message = "This person is too old to be an employee.") Integer age, @NotBlank(message = "required") String joinDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.joinDate = joinDate;
    }

    public EmployeeRequest() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}

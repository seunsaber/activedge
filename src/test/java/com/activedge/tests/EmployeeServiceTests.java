package com.activedge.tests;

import com.activedge.assessment.configs.EmployeeData;
import com.activedge.assessment.dtos.EmployeeRequest;
import com.activedge.assessment.entities.Employee;
import com.activedge.assessment.exceptions.InvalidDateException;
import com.activedge.assessment.exceptions.ResourceAlreadyExistsException;
import com.activedge.assessment.exceptions.ResourceNotFoundException;
import com.activedge.assessment.services.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.HashMap;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeServiceTests {
    private EmployeeService service;
    @BeforeAll
    public void init(){
        HashMap<String, Employee> data =  new HashMap<String, Employee>(){{
            put("E00001", new Employee("E00001", "John", "Keynes", 29, LocalDate.now()));
            put("E00002", new Employee("E00002", "Sarah", "Robinson", 54, LocalDate.now()));
        }};

        service = new EmployeeService(data);
    }
    @Test
    public void shouldCreateEmployee(){
        EmployeeRequest employeeRequest = new EmployeeRequest("E000009","Seun","Fapohunda",56,"2021-04-29");
        Employee employee = service.insert(employeeRequest);
        Assertions.assertEquals(employeeRequest.getEmployeeId(), employee.getEmployeeId());
    }
    @Test
    public void shouldRetrieveEmployee_whenEmployeeIDisValid(){
        Employee employee = service.getEmployee("E00001");
        Assertions.assertNotNull(employee);
    }

    @Test
    public void shouldThrowException_whenEmployeeIDisInvalid(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.getEmployee("invalidID");
        });
    }

    @Test
    public void shouldThrowException_whenEmployeeIDAlreadyExists(){
        EmployeeRequest employeeRequest = new EmployeeRequest("E00001","Seun","Fapohunda",56,"2021-04-29");
        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> {
            service.insert(employeeRequest);
        });
    }

    @Test
    public void shouldRThrowException_whenInvalidDateIsSupplied(){
        EmployeeRequest employeeRequest = new EmployeeRequest("E00006","Seun","Fapohunda",56,"2021-13-32");
        Assertions.assertThrows(InvalidDateException.class, () -> {
            service.insert(employeeRequest);
        });
    }

    @Test
    public void shouldThrowException_whenEmployeeIDisInvalid_duringUpdateAttempt(){
        EmployeeRequest employeeRequest = new EmployeeRequest("E0XXX","Seun","Fapohunda",56,"2021-13-32");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update("E0XXX", employeeRequest);
        });
    }

    @Test
    public void shouldUpdateEmployee_whenValidDataIsSupplied(){
        EmployeeRequest employeeRequest = new EmployeeRequest("E00001","Seun","Fapohunda",56,"2021-04-29");
        Employee employee = service.update("E00001", employeeRequest);
        Assertions.assertEquals(employeeRequest.getFirstName(), employee.getFirstName());
    }

    @Test
    public void shouldDeleteEmployeeRecord_whenIDisValid(){
        String employeeId = "E00001";
        Employee employee = service.getEmployee(employeeId);
        Assertions.assertNotNull(employee);//employee exists,
        Employee employee1 = service.delete(employeeId);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.getEmployee(employeeId);
        });
    }

    @Test
    public void shouldIncrementCount_whenEmployeeIsCreated(){
        Integer currentCount = service.getAll().size();
        EmployeeRequest employeeRequest = new EmployeeRequest("E00007","Seun","Fapohunda",56,"2021-04-29");
        Employee employee = service.insert(employeeRequest);
        Integer newCount = service.getAll().size();
        Assertions.assertEquals(newCount, currentCount + 1);
    }
}

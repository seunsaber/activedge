package com.activedge.assessment.services;

import com.activedge.assessment.dtos.EmployeeRequest;
import com.activedge.assessment.entities.Employee;
import com.activedge.assessment.exceptions.InvalidDateException;
import com.activedge.assessment.exceptions.ResourceAlreadyExistsException;
import com.activedge.assessment.exceptions.ResourceNotFoundException;
import com.activedge.assessment.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private HashMap<String ,Employee> employees;

    public List<Employee> getAll(){
        List<Employee> employees = new ArrayList<>();
        for(Map.Entry<String, Employee> entry : this.employees.entrySet()) {
            employees.add(entry.getValue());
        }
        return employees;
    }

    public Employee get(String employeeId){
        return employees.get(employeeId);
    }

    public Employee getEmployee(String id){
        Employee employee = get(id);
        if(employee == null)
            throw new ResourceNotFoundException("Couldn't find employee with ID: "+ id);
        return employee;
    }

    public Employee insert(EmployeeRequest request){
        Employee existingEmployee = get(request.getEmployeeId());
        if(existingEmployee != null)
            throw new ResourceAlreadyExistsException("An Employee with ID: "+ existingEmployee.getEmployeeId() +" already exists.");

        LocalDate joinDate = null;
        try{
            joinDate = LocalDate.parse(request.getJoinDate(), DateUtil.getDateFormatter());
        }catch (Exception e){
            throw new InvalidDateException();
        }

        Employee employee = new Employee(request.getEmployeeId(),request.getFirstName(),request.getLastName(),request.getAge(),joinDate);
        employees.put(request.getEmployeeId(), employee);
        return employee;
    }

    public Employee update(String id, EmployeeRequest request){
        Employee oldEmployeeData = get(id);
        if(oldEmployeeData == null)
            throw new ResourceNotFoundException("Couldn't find employee with ID: "+ id);

        LocalDate joinDate = null;
        try{
            joinDate = LocalDate.parse(request.getJoinDate(), DateUtil.getDateFormatter());
        }catch (Exception e){
            throw new InvalidDateException();
        }

        Employee employee = new Employee(request.getEmployeeId(),request.getFirstName(),request.getLastName(),request.getAge(),joinDate);
        employees.put(id, employee);
        return employee;
    }

    public Employee delete(String id){
        Employee employee = get(id);
        if(employee == null)
            throw new ResourceNotFoundException("Couldn't find employee with ID: "+ id);

        employees.remove(id);
        return employee;
    }
}

package com.activedge.assessment.services;

import com.activedge.assessment.entities.Employee;
import com.activedge.assessment.exceptions.ResourceAlreadyExistsException;
import com.activedge.assessment.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Employee insert(Employee employee){
        Employee existingEmployee = get(employee.getEmployeeId());
        if(existingEmployee != null)
            throw new ResourceAlreadyExistsException("An Employee with ID: "+ existingEmployee.getEmployeeId() +" already exists.");

        employees.put(employee.getEmployeeId(), employee);
        return employee;
    }

    public Employee update(String id, Employee employee){
        Employee oldEmployeeData = get(id);
        if(oldEmployeeData == null)
            throw new ResourceNotFoundException("Couldn't find employee with ID: "+ id);

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

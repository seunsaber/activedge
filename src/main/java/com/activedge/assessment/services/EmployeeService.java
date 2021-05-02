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
    private HashMap<String ,Employee> employeeData;

    public List<Employee> getAll(){
        List<Employee> employees = new ArrayList<>();
        for(Map.Entry<String, Employee> entry : employeeData.entrySet()) {
            employees.add(entry.getValue());
        }
        return employees;
    }

    public Employee get(String employeeId){
        return employeeData.get(employeeId);
    }

    public Employee insert(Employee employee){
        Employee existingEmployee = get(employee.getEmployeeId());
        if(employee != null)
            throw new ResourceAlreadyExistsException("An Employee with ID: "+ existingEmployee.getEmployeeId() +" already exists.");

        employeeData.put(employee.getEmployeeId(), employee);
        return employee;
    }

    public Employee update(String id, Employee employee){
        Employee oldEmployeeData = get(id);
        if(oldEmployeeData == null)
            throw new ResourceNotFoundException("Couldn't find employee with ID: "+ id);

        employeeData.put(id, employee);
        return employee;
    }

    public Employee delete(String id){
        Employee employee = get(id);
        if(employee == null)
            throw new ResourceNotFoundException("Couldn't find employee with ID: "+ id);

        employeeData.remove(id);
        return employee;
    }
}

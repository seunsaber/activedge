package com.activedge.assessment.services;

import com.activedge.assessment.entities.Employee;
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

    public void insert(Employee employee){
        employeeData.put(employee.getEmployeeId(), employee);
    }

    public void update(String id, Employee employee){
        Employee oldEmplyeeData = get(id);
        if(oldEmplyeeData == null)
            //throw not found
        employeeData.put(id, employee);
    }

    public void delete(String employeeId){
        employeeData.remove(employeeId);
    }
}

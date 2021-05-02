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
    private HashMap<String ,Employee> data;

    public List<Employee> getAll(){
        List<Employee> employees = new ArrayList<>();
        for(Map.Entry<String, Employee> entry : data.entrySet()) {
            employees.add(entry.getValue());
        }
        return employees;
    }

    public void upsert(Employee employee){
        //inserts if key doesn't exist, updates if it does.
        data.put(employee.getEmployeeId(), employee);
    }

    public void delete(String employeeId){
        data.remove(employeeId);
    }
}

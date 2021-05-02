package com.activedge.assessment.api.controllers;

import com.activedge.assessment.entities.Employee;
import com.activedge.assessment.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping
    public void create(@RequestBody Employee request) {
        service.insert(request);
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable String id){
        return service.get(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody Employee request){
        service.update(id,request);
    }

    @PostMapping("/{id}")
    public void delete (@PathVariable String id){
        service.delete(id);
    }
}
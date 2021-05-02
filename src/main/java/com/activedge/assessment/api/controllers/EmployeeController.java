package com.activedge.assessment.api.controllers;

import com.activedge.assessment.entities.Employee;
import com.activedge.assessment.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee request) {
        return new ResponseEntity<Employee>(service.insert(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<List<Employee>>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String id){
        return new ResponseEntity<Employee>(service.getEmployee(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable String id, @RequestBody Employee request){
        return new ResponseEntity<Employee>(service.update(id,request), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Employee> delete (@PathVariable String id){
        return new ResponseEntity<Employee>(service.delete(id), HttpStatus.OK);
    }
}
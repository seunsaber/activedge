package com.activedge.assessment.configs;

import com.activedge.assessment.entities.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;

@Component
public class EmployeeData {
    @Bean
    public HashMap<String, Employee> data () {
        return new HashMap<String, Employee>(){{
            put("E00001", new Employee("E00001", "John", "Keynes", 29, LocalDate.now()));
            put("E00002", new Employee("E00002", "Sarah", "Robinson", 54, LocalDate.now()));
        }};
    }
}

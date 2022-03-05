package com.mamun.crud.controller;

import java.util.List;

import com.mamun.crud.exception.ResourceNotFoundException;
import com.mamun.crud.model.Employee;
import com.mamun.crud.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
       return employeeRepository.findAll(); 
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
          return this.employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity <Employee> getEmployeeBYId(@PathVariable Long id){
           Employee employee= this.employeeRepository.findById(id).
           orElseThrow(() -> new ResourceNotFoundException("Emplyee doesn't exist with this particular id" +id));
           return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeeDetails){

       Employee employee= this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee doesn't exist with this id" +id));

       employee.setFirstName(employeeeDetails.getFirstName());
       employee.setLastName(employeeeDetails.getLastName());
       employee.setEmail(employeeeDetails.getEmail());
       
       Employee updatedEmployee= this.employeeRepository.save(employee);
       return ResponseEntity.ok(updatedEmployee);

    }

    @DeleteMapping("/employees/{id}")
       public void deleteEmployee(@PathVariable Long id){
          Employee emp=this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee doesn't exist with this id" +id));
          this.employeeRepository.delete(emp);
       }
    }


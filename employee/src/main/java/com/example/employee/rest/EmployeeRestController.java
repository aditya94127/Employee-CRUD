package com.example.employee.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService=theEmployeeService;
	}
	
	@GetMapping("/employees")
	public List <Employee> findAll(){
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{theId}")
	public Employee getEmployee(@PathVariable int theId) {
		Employee theEmployee=employeeService.findById(theId);
		if(theEmployee==null) {
			throw new RuntimeException("Employee not found with the Id:"+ theId);
		}
		return theEmployee;
	}
	
	@DeleteMapping("/employees/{theId}")
	public String deleteEmployee(@PathVariable int theId) {
		Employee theEmployee=employeeService.findById(theId);
		if(theEmployee==null) {
			throw new RuntimeException("Employee not found with the Id:"+ theId);
		}
		employeeService.deleteById(theId);
		return "Deleted employee id "+ theEmployee;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		return employeeService.save(theEmployee);
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		
		Employee dbEmployee=employeeService.save(theEmployee);
		return dbEmployee;
	}
	
	@PutMapping("/employees/{theId}")
	public String updateEmployeeById(@PathVariable int theId,@RequestBody Employee theEmployee) {
		
		Employee dbEmployee=employeeService.findById(theId);
		if(dbEmployee==null) {
			throw new RuntimeException("Employee not found with the Id:"+ theId);
		}
		return "Updated Employee Details with "+theId+ ":"+ employeeService.updateEmployeeById(theId, theEmployee);
	}
	
}

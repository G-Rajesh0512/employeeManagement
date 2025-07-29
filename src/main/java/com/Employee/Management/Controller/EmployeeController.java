package com.Employee.Management.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Management.Entity.Employeeinfo;
import com.Employee.Management.Interface.EmployeeRepo;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepo employeerepo;
	
	@PostMapping("/api/employees")
	public ResponseEntity<Employeeinfo> saveEmployeeinfo(@RequestBody Employeeinfo employee) {
		return new ResponseEntity<>(employeerepo.save(employee),HttpStatus.CREATED);
	}
	@GetMapping("/api/employee")
	public ResponseEntity<List<Employeeinfo>> getEmployeeinfo(){
		return new ResponseEntity<>(employeerepo.findAll(),HttpStatus.OK);
	}
	@GetMapping("/api/employees/{empId}")
	public ResponseEntity<Employeeinfo> getEmployeeinfo(@PathVariable Long empId){
		Optional<Employeeinfo> employee = employeerepo.findById(empId);
		if(employee.isPresent()) {
			return new ResponseEntity<>(employee.get(),HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/api/employees/{empId}")
	public ResponseEntity<Employeeinfo> updateEmployeeinfo(@PathVariable Long empId,@RequestBody Employeeinfo emp){
		Optional<Employeeinfo> employee = employeerepo.findById(empId);
		if(employee.isPresent()) {
			employee.get().setEmpId(emp.getEmpId());
			employee.get().setEmpName(emp.getEmpName());
			employee.get().setEmpEmail(emp.getEmpEmail());
			employee.get().setEmpDept(emp.getEmpDept());
			return new ResponseEntity<>(employeerepo.save(employee.get()),HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/api/employees/{empId}")
	public ResponseEntity<Void> deleteEmployeeinfo(@PathVariable Long empId){
		Optional<Employeeinfo> employee = employeerepo.findById(empId);
		if(employee.isPresent()) {
			employeerepo.deleteById(empId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

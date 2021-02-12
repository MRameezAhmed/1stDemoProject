package com.nisum.controller;

import com.nisum.model.Project;
import com.nisum.service.EmployeeService;
import com.nisum.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();

    }

    @GetMapping("projects/{projectId}")
    public List<Employee> getAllEmployees(@PathVariable int projectId){
        return employeeService.getAllEmployees(projectId);

    }
    @GetMapping("{id}")
    public  Employee getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);

    }

    @PostMapping("projects/{projectId}")
    public void addEmployee(@RequestBody Employee emp, @PathVariable int projectId){
        emp.setProject(new Project(projectId,"","", LocalDateTime.now(),"", LocalDateTime.now(),""));
        employeeService.addEmployee(emp);

    }

    @PutMapping("{id}/projects/{projectId}")
    public void updateEmployee(@RequestBody Employee emp,@PathVariable int projectId){
        emp.setProject(new Project(projectId,"","",LocalDateTime.now(),"", LocalDateTime.now(),""));
        employeeService.updateEmployee(emp);

    }

    @DeleteMapping("{id}/projects/{projectId}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);

    }

}

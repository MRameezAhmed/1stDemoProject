package com.nisum.service;

import com.nisum.repository.EmployeeRepository;
import com.nisum.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){

        List<Employee> employees=new ArrayList<>();
        employeeRepository.findAll()
                .forEach(employees::add);

        return employees;
    }


    public List<Employee> getAllEmployees(int id){

        List<Employee> employees=new ArrayList<>();
        employeeRepository.findByProjectId(id)
                .forEach(employees::add);

        return employees;
    }

    public Employee getEmployee(int id){
       return employeeRepository.findById(id).get();
    }

    public void addEmployee(Employee emp) {
        employeeRepository.save(emp);

    }

    public void updateEmployee(Employee emp) {
        employeeRepository.save(emp);

    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);

    }
}

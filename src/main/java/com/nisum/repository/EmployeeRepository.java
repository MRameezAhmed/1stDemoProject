package com.nisum.repository;

import com.nisum.model.Employee;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    public List<Employee> findByProjectId(int id);
}
package com.nisum.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Employee {

    @Id
    private  int id;
    private String name;
    private String department;

    @ManyToOne
    private Project project;



    public Employee(){}

    public Employee(int id, String name, String department, int projectId) {
        super();
        this.id = id;
        this.name = name;
        this.department = department;
        this.project=new Project(projectId,"","", LocalDateTime.now(),"", LocalDateTime.now(),"");
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

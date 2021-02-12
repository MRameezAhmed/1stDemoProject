package com.nisum.controller;

import com.nisum.model.Project;
import com.nisum.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService  projectService;

    @GetMapping
    public List<Project> getAllProjects(){

        return projectService.getAllProjects();

    }
    @GetMapping("{id}")
    public Project getProject(@PathVariable int id){
        return projectService.getProject(id);

    }

    @PostMapping
    public void addProject(@RequestBody Project proj){
        projectService.addProject(proj);
    }

    @PutMapping("{id}")
    public void updateProject(@RequestBody Project proj, @PathVariable int id){
        projectService.updateProject(id, proj);
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable int id){
        projectService.deleteProject(id);

    }

}

package com.nisum.service;

import com.nisum.repository.ProjectRepository;
import com.nisum.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects(){

        List<Project> projects =new ArrayList<>();
        projectRepository.findAll()
                .forEach(projects::add);

        return projects;
    }

    public Project getProject(int id){
       return projectRepository.findById(id).get();
    }

    public Project addProject(Project proj) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        proj.setCreatedBy(username);
        proj.setUpdatedBy(username);
        proj.setCreatedAt(LocalDateTime.now());
        proj.setUpdatedAt(LocalDateTime.now());
        return projectRepository.save(proj);

    }

    public Project updateProject(int id, Project proj) {
        proj.setUpdatedAt(LocalDateTime.now());
        return projectRepository.save(proj);

    }

    public Project deleteProject(int id) {
        projectRepository.deleteById(id);
        return null;
    }
}

package com.nisum.service;

import com.nisum.repository.ProjectRepository;
import com.nisum.model.Project;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.springframework.security.access.method.P;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;
    @Rule
    public MockitoRule rule= MockitoJUnit.rule();

    @Test
    public void testGetAllProducts(){
        Mockito.when(projectRepository.findAll()).thenReturn(listStub());
        List<Project> projects = projectService.getAllProjects();
        Assertions.assertEquals(listStub(),projects);

    }
    @Test
    public void testAddProject(){

        Project project=new Project(111, "abc", "hello world", LocalDateTime.now(),"", LocalDateTime.now(),"");
        Mockito.when(projectRepository.save(project)).thenReturn(project);

        Assertions.assertEquals(project, projectService.addProject(project));
    }

    private List<Project> listStub() {
        List<Project> projects = new ArrayList<>( Arrays.asList(
                new Project(2, "java name", "java  description",LocalDateTime.now(),"", LocalDateTime.now(),""),
                new Project(5, "java name", "java  description", LocalDateTime.now(),"", LocalDateTime.now(),"")
        ));
        return  projects;
    }


}

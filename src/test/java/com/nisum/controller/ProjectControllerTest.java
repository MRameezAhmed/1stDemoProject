package com.nisum.controller;

import com.nisum.model.Project;
import com.nisum.service.ProjectService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.omg.CORBA.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.method.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sun.plugin2.util.PojoUtil.toJson;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @Test
    @WithMockUser(username = " ", password = " ", roles = "USER")
    public void testGetAllProjects() throws Exception {

        Mockito.when(projectService.getAllProjects()).thenReturn(listStub());

        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":2,\"name\":\"java name\",\"description\":\"java  description\"}" +
                        ",{\"id\":5,\"name\":\"project2\",\"description\":\"Ecommerce\"}]"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = " ", password = " ", roles = "USER")
    public void testGetProject() throws Exception {
        Project project=new Project(2, "java name", "java  description", LocalDateTime.now(),"", LocalDateTime.now(),"");
        Mockito.when(projectService.getProject(anyInt())).thenReturn(project);

        mockMvc.perform(get("/projects/99999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("java name"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = " ", password = " ", roles = "ADMIN")
    public void testAddProject() throws Exception {
        Project mockproject=new Project(2, "java name", "java  description",LocalDateTime.now(),"", LocalDateTime.now(),"");
        Mockito.when(projectService.addProject(any())).thenReturn(mockproject);

        Project addproject=new Project(2, "java name", "java  description",LocalDateTime.now(),"", LocalDateTime.now(),"");
        mockMvc.perform(post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(addproject)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = " ", password = " ", roles = "ADMIN")
    public void testUpdateProject() throws Exception{
        Project mockproject=new Project(2, "java name", "java  description",LocalDateTime.now(),"", LocalDateTime.now(),"");
        Mockito.when(projectService.updateProject(anyInt(),any())).thenReturn(mockproject);

        Project addproject=new Project(2, "java name", "java  description",LocalDateTime.now(),"", LocalDateTime.now(),"");
        mockMvc.perform(put("/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(addproject)))
                .andExpect(status().isOk())
                .andReturn();

    }
    @Test
    @WithMockUser(username = " ", password = " ", roles = "ADMIN")
    public void testDeleteProject() throws Exception {
        Mockito.doNothing().when(projectService.deleteProject(anyInt()));

        mockMvc.perform(delete("/projects/id"))
                .andExpect(status().isOk())
                .andReturn();
    }


    private List<Project> listStub() {
        List<Project> projects = new ArrayList<>( Arrays.asList(
                new Project(2, "java name", "java  description",LocalDateTime.now(),"", LocalDateTime.now(),""),
                new Project(5, "project2", "Ecommerce",LocalDateTime.now(),"", LocalDateTime.now(),"")
        ));
        return  projects;
    }




}

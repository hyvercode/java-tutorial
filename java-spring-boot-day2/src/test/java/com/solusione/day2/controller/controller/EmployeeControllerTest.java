package com.solusione.day2.controller.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solusione.day2.controller.EmployeeController;
import com.solusione.day2.model.entity.Employee;
import com.solusione.day2.model.request.EmployeeRequest;
import com.solusione.day2.model.response.EmployeeResponse;
import com.solusione.day2.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;
    @Mock
    EmployeeService employeeService;


    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        employeeController = new EmployeeController(employeeService);
        mockMvc = standaloneSetup(employeeController).build();
    }

    @Test
    void getListEmployeeTest() throws Exception {

        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Solusi")
                .lastName("One")
                .email("contact@solusine.id")
                .build();

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        //WHEN
        when(employeeService.list()).thenReturn(employeeList);

        mockMvc.perform(get("/api/employees")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }


    @Test
    void getPaginate() throws Exception {
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Solusi")
                .lastName("One")
                .email("contact@solusine.id")
                .build();

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        //WHEN
        when(employeeService.paginate(0,10)).thenReturn(employeeList);

        mockMvc.perform(get("/api/employees/paginate?pageNo=0&pageSize=10")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void postCreateEmployeeTest() throws Exception {
        EmployeeRequest employeeRequest = EmployeeRequest.builder()
                .firstName("Solusi")
                .lastName("One")
                .email("contact@solusine.id")
                .build();

        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .id(99L)
                .firstName("Solusi")
                .lastName("One")
                .email("contact@solusine.id")
                .build();

        //WHEN
        when(employeeService.create(employeeRequest)).thenReturn(employeeResponse);

        mockMvc.perform(post("/api/employees")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(employeeRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void postUpdateEmployeeTest() throws Exception {
        EmployeeRequest employeeRequest = EmployeeRequest.builder()
                .firstName("Solusi")
                .lastName("One")
                .email("contact@solusine.id")
                .build();

        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .id(1L)
                .firstName("Solusi")
                .lastName("One")
                .email("contact@solusine.id")
                .build();

        //WHEN
        when(employeeService.update(1L,employeeRequest)).thenReturn(employeeResponse);

        mockMvc.perform(put("/api/employees/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(employeeRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void getReadEmployeeTest() throws Exception {
        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .id(1L)
                .firstName("Solusi")
                .lastName("One")
                .email("contact@solusine.id")
                .build();

        //WHEN
        when(employeeService.read(1L)).thenReturn(employeeResponse);

        mockMvc.perform(get("/api/employees/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

}

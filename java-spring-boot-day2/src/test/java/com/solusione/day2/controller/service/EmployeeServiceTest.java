package com.solusione.day2.controller.service;

import com.solusione.day2.model.entity.Employee;
import com.solusione.day2.model.request.EmployeeRequest;
import com.solusione.day2.model.response.EmployeeResponse;
import com.solusione.day2.repository.EmployeeRepository;
import com.solusione.day2.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    public static final Employee EMPLOYEE = Employee.builder()
            .id(1L)
            .firstName("Solusi")
            .lastName("One")
            .email("contact@solusine.id")
            .build();
    public static final EmployeeRequest EMPLOYEE_REQUEST = EmployeeRequest.builder()
            .firstName("Solusi")
            .lastName("One")
            .email("contact@solusine.id")
            .build();

    public static final EmployeeResponse EMPLOYEE_RESPONSE = EmployeeResponse.builder()
            .id(1L)
            .firstName("Solusi")
            .lastName("One")
            .email("contact@solusine.id")
            .build();

    public static final Page<Employee> EMPLOYEE_PAGE = new PageImpl(Collections.singletonList(EMPLOYEE));

    public List<Employee> employeeList =new ArrayList<>();


    @BeforeEach
    void setUp() {
        initMocks(this);
        employeeList.add(EMPLOYEE);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(modelMapper);
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    void getList() {
        Page<Employee> employees = mock(Page.class);
        when(employees.getContent()).thenReturn(Collections.singletonList(EMPLOYEE));
        Pageable pageableTest = PageRequest.of(0, 1);
        when(employeeRepository.findAll(eq(pageableTest))).thenReturn(EMPLOYEE_PAGE);

        List<Employee> baseResponse = employeeService.list();

        verify(employeeRepository).findAll(eq(pageableTest));
        verify(modelMapper).map(EMPLOYEE,EmployeeRequest.class);
    }
}

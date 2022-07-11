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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

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

    public List<Employee> employeeList =new ArrayList<>();


    @BeforeEach
    void setUp() {
        employeeList.add(EMPLOYEE);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(modelMapper);
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    void listTest() throws Exception {
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> actual = employeeService.list();

        assertNotNull(actual);
        verify(employeeRepository).findAll();
    }

    @Test
    void paginateTest() throws Exception {
        Page<Employee> pagedResult = new PageImpl<>(employeeList);
        Pageable pageableTest = PageRequest.of(0, 10);
        when(employeeRepository.findAll(eq(pageableTest))).thenReturn(pagedResult);
        List<Employee> actual = employeeService.paginate(0,10);

        assertEquals(actual,employeeList);
        verify(employeeRepository).findAll(eq(pageableTest));
    }


    @Test
    void createTest() throws Exception {
        when(employeeRepository.save(any(Employee.class))).thenReturn(EMPLOYEE);

        EmployeeResponse actual = employeeService.create(EMPLOYEE_REQUEST);

        assertNotNull(actual.getId());
        assertEquals(actual.getFirstName(),EMPLOYEE.getFirstName());
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void updateTest() throws Exception {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(EMPLOYEE));
        when(employeeRepository.save(any(Employee.class))).thenReturn(EMPLOYEE);

        EmployeeResponse actual = employeeService.update(anyLong(),EMPLOYEE_REQUEST);

        assertNotNull(actual.getId());

        verify(employeeRepository).findById(anyLong());
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void deleteTest() throws Exception {
        willDoNothing().given(employeeRepository).deleteById(anyLong());
        employeeService.delete(anyLong());
        verify(employeeRepository).deleteById(anyLong());
    }
}

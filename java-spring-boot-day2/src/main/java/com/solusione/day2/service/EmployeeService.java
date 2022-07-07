package com.solusione.day2.service;

import com.solusione.day2.model.entity.Employee;
import com.solusione.day2.model.request.EmployeeRequest;
import com.solusione.day2.model.response.EmployeeResponse;
import com.solusione.day2.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> paginate(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Employee> pagedResult = employeeRepository.findAll(paging);

        return pagedResult.toList();
    }

    public List<Employee> list() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public EmployeeResponse create(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .build();

        //Save records
        Employee response = employeeRepository.save(employee);

        return EmployeeResponse.builder()
                .id(response.getId())
                .firstName(response.getFirstName())
                .lastName(response.getLastName())
                .email(response.getEmail())
                .build();
    }

    public EmployeeResponse update(Long id, EmployeeRequest employeeRequest) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            return new EmployeeResponse();
        }

        Employee newEmployee = employeeOptional.get();
        newEmployee.setFirstName(employeeRequest.getFirstName());
        newEmployee.setLastName(employeeRequest.getLastName());
        newEmployee.setEmail(employeeRequest.getEmail());

        //Save records
        Employee response = employeeRepository.save(newEmployee);

        return EmployeeResponse.builder()
                .id(response.getId())
                .firstName(response.getFirstName())
                .lastName(response.getLastName())
                .email(response.getEmail())
                .build();
    }

    public EmployeeResponse read(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isEmpty()) {
            return new EmployeeResponse();
        }

        Employee employee = employeeOptional.get();

        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();

    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
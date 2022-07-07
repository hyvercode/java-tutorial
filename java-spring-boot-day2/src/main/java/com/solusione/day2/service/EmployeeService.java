package com.solusione.day2.service;

import com.solusione.day2.model.entity.Employee;
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


    public List<Employee> paginate(int pageNo,int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Employee> pagedResult = employeeRepository.findAll(paging);

        return pagedResult.toList();
    }

    public List<Employee> list() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isEmpty()){
          return new Employee();
        }

        Employee newEmployee = employeeOptional.get();
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setEmail(employee.getEmail());
        return employeeRepository.save(newEmployee);
    }

    public Employee read(Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isEmpty()){
           return new Employee();
        }

        return  employeeOptional.get();
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }
}
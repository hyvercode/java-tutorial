package com.solusione.day2.controller;

import com.solusione.day2.model.entity.Employee;
import com.solusione.day2.model.request.EmployeeRequest;
import com.solusione.day2.model.response.EmployeeResponse;
import com.solusione.day2.service.EmployeeService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getListEmployee(){
        return employeeService.list();
    }

    @GetMapping(value = "/paginate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getPaginante(@Param("pageNo") int pageNo,@Param("pageSize") int pageSize){
        return employeeService.paginate(pageNo,pageSize);
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeResponse postCreateEmployee(@RequestBody EmployeeRequest employeeRequest){
        return employeeService.create(employeeRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeResponse postUpdateEmployee(@PathVariable Long id,@RequestBody EmployeeRequest employeeRequest){
        return employeeService.update(id,employeeRequest);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeResponse getReadEmployee(@PathVariable Long id){
        return employeeService.read(id);
    }

    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReadEmployee(@PathVariable Long id){
        employeeService.delete(id);
    }
}

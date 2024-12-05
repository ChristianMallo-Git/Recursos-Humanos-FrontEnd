package cm.rh.controller;

import cm.rh.exception.ResourceNotFoundException;
import cm.rh.model.Employee;
import cm.rh.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
//http://localhost:8080/
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    public static final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeController.class);

    //http://localhost:8080/rh-app/employees
    @GetMapping("/employees")
    public List<Employee> listEmployees(){
        List<Employee> listEmployee = iEmployeeService.listEmployee();
        listEmployee.forEach((employee)->LOGGER.info(employee.toString()));
        return listEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        LOGGER.info("Employee to add: " + employee);
        return iEmployeeService.saveEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id){
        Employee employee = iEmployeeService.searchEmployeeById(id);
        if(employee==null){
         throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> upDateEmployee(@PathVariable Integer id,
                                                   @RequestBody Employee employeeReceived){
        Employee employee = iEmployeeService.searchEmployeeById(id);
        if (employee == null){
            throw new ResourceNotFoundException("The id received does not exist: " + id);
        }
        employee.setNameEmployee(employeeReceived.getNameEmployee());
        employee.setDepartmentEmployee(employeeReceived.getDepartmentEmployee());
        employee.setSalaryEmployee(employeeReceived.getSalaryEmployee());
        iEmployeeService.saveEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Integer id){
        Employee employee = iEmployeeService.searchEmployeeById(id);
        if(employee==null){
            throw new ResourceNotFoundException("The id received does not exist: " + id);
        }
        iEmployeeService.deleteEmployee(employee);
        // Json {"eliminado": "true"}
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

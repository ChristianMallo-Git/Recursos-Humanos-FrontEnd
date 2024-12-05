package cm.rh.service;

import cm.rh.model.Employee;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> listEmployee();

    public Employee searchEmployeeById(Integer idEmployee);

    public Employee saveEmployee (Employee employee);

    public void deleteEmployee (Employee employee);

}

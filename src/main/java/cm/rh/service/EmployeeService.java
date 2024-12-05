package cm.rh.service;

import cm.rh.model.Employee;
import cm.rh.repository.IRespositoryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    private IRespositoryEmployee iRespositoryEmployee;

    @Override
    public List<Employee> listEmployee() {
        return iRespositoryEmployee.findAll();
    }

    @Override
    public Employee searchEmployeeById(Integer idEmployee) {
        Employee employee = iRespositoryEmployee.findById(idEmployee).orElse(null);
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return iRespositoryEmployee.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        iRespositoryEmployee.delete(employee);
    }
}

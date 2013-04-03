package payroll.repository;

import payroll.Employee;

import java.util.List;

public interface EmployeeRepository {

    void save(Employee employee);

    Employee getById(String empId);

    List<Employee> getAll();

    void delete(String empId);
}

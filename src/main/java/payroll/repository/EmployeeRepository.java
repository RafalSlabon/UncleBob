package payroll.repository;

import payroll.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    void save(Employee employee);

    Employee getByEmpId(String empId);

    List<Employee> getAll();

    void deleteByEmdId(String empId);
}

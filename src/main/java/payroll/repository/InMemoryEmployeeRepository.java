package payroll.repository;

import payroll.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    private final Map<String, Employee> employees = new HashMap<String, Employee>();

    @Override
    public void save(Employee employee) {
        employees.put(employee.empId, employee);
    }

    @Override
    public Employee getById(String empId) {
        return employees.get(empId);
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<Employee>(employees.values());
    }

    @Override
    public void delete(String empId) {
        employees.remove(empId);
    }
}

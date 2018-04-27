package repository;

import entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findByEmployeeNumber(Integer employeeNumber);
    List<Employee> findByLastNameStartsWithOrderByLastNameDescFirstNameDesc(String lastName);
    List<Employee> findByFirstNameStartsWithOrderByLastNameDescFirstNameDesc(String firstName);
    List<Employee> findByLastNameStartsWithAndFirstNameStartsWithOrderByLastNameDescFirstNameDesc(String lastName, String firstName);
}
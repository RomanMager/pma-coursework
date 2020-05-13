package by.bsuir.coursework.pmacoursework.repository;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByLogin(String login);

    Employee findByLoginOrEmail(String login, String email);

    List<Employee> findEmployeesByActiveIsTrue();
}

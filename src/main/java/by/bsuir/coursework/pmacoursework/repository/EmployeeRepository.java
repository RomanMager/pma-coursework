package by.bsuir.coursework.pmacoursework.repository;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

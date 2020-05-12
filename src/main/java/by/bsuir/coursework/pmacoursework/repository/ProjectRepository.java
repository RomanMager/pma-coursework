package by.bsuir.coursework.pmacoursework.repository;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import by.bsuir.coursework.pmacoursework.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll();

    List<Project> findByName(String name);

    List<Project> findByNameContainingIgnoreCase(String filter);

    List<Project> findAllByAssignedEmployeesEquals(Employee employee);
}

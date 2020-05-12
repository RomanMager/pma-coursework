package by.bsuir.coursework.pmacoursework.repository;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import by.bsuir.coursework.pmacoursework.entity.Project;
import by.bsuir.coursework.pmacoursework.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAllByProject(Project project);

    List<Task> findAllByProjectProjectId(Long projectId);

    List<Task> findTasksByProjectProjectId(Long id);

    List<Task> findAllByAssignedToEquals(Employee employee);
}

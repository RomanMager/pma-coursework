package by.bsuir.coursework.pmacoursework.repository;

import by.bsuir.coursework.pmacoursework.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
}

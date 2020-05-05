package by.bsuir.coursework.pmacoursework.service;

import by.bsuir.coursework.pmacoursework.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}

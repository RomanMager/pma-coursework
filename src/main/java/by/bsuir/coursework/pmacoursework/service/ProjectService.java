package by.bsuir.coursework.pmacoursework.service;

import by.bsuir.coursework.pmacoursework.entity.Project;
import by.bsuir.coursework.pmacoursework.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    // public Optional<List<Project>> findProjectByName(String projectName) {
    //     return projectRepository.findByName(projectName);
    // }

    public List<Project> findProjectByName(String projectName) {
        return projectRepository.findByName(projectName);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }
}

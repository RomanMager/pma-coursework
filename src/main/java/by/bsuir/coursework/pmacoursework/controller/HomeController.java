package by.bsuir.coursework.pmacoursework.controller;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import by.bsuir.coursework.pmacoursework.entity.Project;
import by.bsuir.coursework.pmacoursework.entity.Task;
import by.bsuir.coursework.pmacoursework.repository.ProjectRepository;
import by.bsuir.coursework.pmacoursework.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public HomeController(ProjectRepository projectRepository,
                          TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String homePage(@AuthenticationPrincipal Employee employee, Model model) {
        List<Project> assignedProjects = projectRepository.findAllByAssignedEmployeesEquals(employee);
        List<Task> assignedTasks = taskRepository.findAllByAssignedToEquals(employee);

        model.addAttribute("employee", employee);
        model.addAttribute("assignedProjects", assignedProjects);
        model.addAttribute("assignedTasks", assignedTasks);

        return "home";
    }
}

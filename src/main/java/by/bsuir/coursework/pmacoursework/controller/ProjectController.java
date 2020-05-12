package by.bsuir.coursework.pmacoursework.controller;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import by.bsuir.coursework.pmacoursework.entity.Project;
import by.bsuir.coursework.pmacoursework.entity.ProjectStatus;
import by.bsuir.coursework.pmacoursework.repository.EmployeeRepository;
import by.bsuir.coursework.pmacoursework.repository.ProjectRepository;
import by.bsuir.coursework.pmacoursework.repository.ProjectStatusRepository;
import by.bsuir.coursework.pmacoursework.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository statusRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ProjectController(ProjectService projectService,
                             ProjectRepository projectRepository,
                             ProjectStatusRepository statusRepository,
                             EmployeeRepository employeeRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
        this.statusRepository = statusRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectService.findAll();

        model.addAttribute("projects", projects);

        return "projects/projects-list";
    }

    @PostMapping
    public String filterProject(Model model, @RequestParam String filter) {
        List<Project> projects;

        if (filter != null && !filter.isEmpty()) {
            projects = projectRepository.findByNameContainingIgnoreCase(filter);
        } else {
            projects = projectService.findAll();
        }

        model.addAttribute("projects", projects);

        return "projects/projects-list";
    }

    @GetMapping(value = "/new")
    public String displayNewProjectForm(Model model) {
        Project newProject = new Project();

        List<Employee> activeEmployees = employeeRepository.findEmployeesByActiveIsTrue();
        List<ProjectStatus> projectStatuses = statusRepository.findAll();

        model.addAttribute("activeEmployees", activeEmployees);
        model.addAttribute("project", newProject);
        model.addAttribute("projectStatuses", projectStatuses);

        return "projects/new-project";
    }

    @PostMapping(value = "/save")
    public String createProject(@Valid Project project, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Employee> activeEmployees = employeeRepository.findEmployeesByActiveIsTrue();
            List<ProjectStatus> projectStatuses = statusRepository.findAll();

            model.addAttribute("activeEmployees", activeEmployees);
            model.addAttribute("projectStatuses", projectStatuses);

            return "projects/new-project";
        }
        projectService.save(project);

        return "redirect:/projects";
    }

    // @GetMapping(value = "/{id}")
    // public String viewProject(@PathVariable Long id, Model model) {
    //     Project project = projectRepository.findById(id)
    //                                        .orElseThrow(() -> new IllegalArgumentException("Invalid Project ID:" + id));
    //
    //     model.addAttribute("project", project);
    //
    //     return "projects/project-view";
    // }

    @GetMapping(value = "/edit/{id}")
    public String displayProjectEditForm(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id)
                                           .orElseThrow(() -> new IllegalArgumentException("Invalid Project ID:" + id));
        List<ProjectStatus> projectStatuses = statusRepository.findAll();
        List<Employee> activeEmployees = employeeRepository.findEmployeesByActiveIsTrue();


        model.addAttribute("project", project);
        model.addAttribute("projectStatuses", projectStatuses);
        model.addAttribute("activeEmployees", activeEmployees);

        return "projects/project-edit";
    }

    @PostMapping(value = "/update/{id}")
    public String updateProject(@PathVariable @NotNull Long id, @Valid Project project,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            project.setProjectId(id);
            return "projects/project-edit";
        }

        projectService.save(project);

        return "redirect:/projects";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteProject(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id)
                                           .orElseThrow(() -> new IllegalArgumentException("Invalid Project ID:" + id));

        projectRepository.delete(project);

        return "redirect:/projects";
    }
}

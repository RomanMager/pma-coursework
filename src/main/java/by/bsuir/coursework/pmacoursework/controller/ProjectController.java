package by.bsuir.coursework.pmacoursework.controller;

import by.bsuir.coursework.pmacoursework.entity.Project;
import by.bsuir.coursework.pmacoursework.repository.ProjectRepository;
import by.bsuir.coursework.pmacoursework.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectService projectService,
                             ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectService.findAll();

        model.addAttribute("projects", projects);

        return "projects/projects-list";
    }

    @PostMapping(value = "/filter")
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
        // todo: Add employees to the project
        //  - ? show only employees which are ruled by a specific manager.

        model.addAttribute("project", newProject);

        return "projects/new-project";
    }

    @PostMapping(value = "/save")
    public String createProject(Project project, Model model) {
        projectService.save(project);

        return "redirect:/projects";
    }
}

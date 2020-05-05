package by.bsuir.coursework.pmacoursework.controller;

import by.bsuir.coursework.pmacoursework.entity.Project;
import by.bsuir.coursework.pmacoursework.entity.Task;
import by.bsuir.coursework.pmacoursework.entity.TaskType;
import by.bsuir.coursework.pmacoursework.repository.ProjectRepository;
import by.bsuir.coursework.pmacoursework.repository.TaskRepository;
import by.bsuir.coursework.pmacoursework.repository.TaskTypeRepository;
import by.bsuir.coursework.pmacoursework.service.ProjectService;
import by.bsuir.coursework.pmacoursework.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final TaskTypeRepository taskTypeRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository,
                          ProjectRepository projectRepository,
                          ProjectService projectService,
                          TaskService taskService,
                          TaskTypeRepository taskTypeRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.projectService = projectService;
        this.taskService = taskService;
        this.taskTypeRepository = taskTypeRepository;
    }

    @GetMapping(value = {"/projects/{id}/tasks"})
    public String getAllTasks(@PathVariable(name = "id") Long projectId, Model model) {
        Task task = new Task();
        Project project = projectRepository.findById(projectId)
                                           .orElseThrow(() -> new IllegalArgumentException("Invalid Project ID:" + projectId));

        List<Task> allTasks = taskRepository.findTasksByProjectProjectId(projectId);

        List<Task> availableTasks = allTasks.stream()
                                            .filter(it -> !it.isCompleted())
                                            .collect(Collectors.toList());

        List<Task> completedTasks = allTasks.stream()
                                            .filter(Task::isCompleted)
                                            .collect(Collectors.toList());

        List<TaskType> taskTypes = taskTypeRepository.findAll();

        model.addAttribute("project", project);
        model.addAttribute("task", task);
        model.addAttribute("availableTasks", availableTasks);
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("taskTypes", taskTypes);

        return "projects/project-view";
    }

    @PostMapping(value = "/projects/{id}/tasks/new")
    public String addNewTask(@PathVariable(name = "id") Long projectId, @Valid Task task,
                             BindingResult bindingResult, Model model) {

        Project project = projectRepository.findById(projectId)
                                           .orElseThrow(() -> new IllegalArgumentException("Invalid Project ID:" + projectId));
        task.setProject(project);
        taskRepository.save(task);

        return getRedirectPage(projectId);
    }

    private String getRedirectPage(Long projectId) {
        return "redirect:/projects/" + projectId + "/tasks";
    }

    @GetMapping(value = "/projects/{id}/tasks/{taskId}/edit")
    public String showTaskEditForm(@PathVariable(name = "id") Long projectId, @PathVariable Long taskId, Model model) {
        Task task = taskRepository.findById(taskId).get();
        List<TaskType> taskTypes = taskTypeRepository.findAll();

        model.addAttribute("task", task);
        model.addAttribute("taskTypes", taskTypes);
        model.addAttribute("project", task.getProject());
        model.addAttribute("projectId", projectId);

        return "tasks/task-edit";
    }

    @PostMapping(value = "/projects/{id}/tasks/update")
    public String updateTask(@PathVariable(name = "id") Long projectId,
                             @Valid Task task, Model model) {
        taskRepository.save(task);

        return getRedirectPage(projectId);
    }

    @PostMapping(value = "/projects/{id}/tasks/delete")
    public String deleteTask(@PathVariable(name = "id") Long projectId, @RequestParam Long taskId, Model model) {
        taskService.deleteById(taskId);
        return getRedirectPage(projectId);
    }

    @PostMapping(value = "/projects/{id}/tasks/{taskId}/status")
    public String changeStatus(@PathVariable(name = "id") Long projectId, @PathVariable Long taskId,
                               @RequestParam String status, Model model) {

        Task task = taskRepository.findById(taskId).get();

        switch (status.toLowerCase()) {
            case "complete":
                task.setCompleted(true);
                break;
            case "uncomplete":
                task.setCompleted(false);
                break;
        }

        taskRepository.save(task);

        return getRedirectPage(projectId);
    }
}

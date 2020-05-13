package by.bsuir.coursework.pmacoursework.controller;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import by.bsuir.coursework.pmacoursework.repository.EmployeeRepository;
import by.bsuir.coursework.pmacoursework.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    @Autowired
    public ProfileController(EmployeeRepository employeeRepository,
                             EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String displayProfile(@AuthenticationPrincipal Employee employee, Model model) {
        model.addAttribute("employee", employee);
        return "profile/profile";
    }

    @GetMapping(value = "/edit")
    public String displayEditForm(@AuthenticationPrincipal Employee employee, Model model) {
        model.addAttribute("employee", employee);
        return "profile/profile-edit";
    }

    @PostMapping(value = "/update")
    public String updateProfile(@AuthenticationPrincipal Employee principal,
                                Employee updated,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors() || bindingResult.hasFieldErrors()) {
            return "profile/profile-edit";
        }

        employeeService.updateProfile(principal, updated);

        return "redirect:/profile";
    }

}

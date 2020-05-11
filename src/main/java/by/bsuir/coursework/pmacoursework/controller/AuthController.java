package by.bsuir.coursework.pmacoursework.controller;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import by.bsuir.coursework.pmacoursework.entity.Role;
import by.bsuir.coursework.pmacoursework.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class AuthController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "/registration")
    public String displayRegistrationForm() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registerUser(Employee employee, Model model) {
        Employee employeeFromDB = employeeRepository.findByLogin(employee.getLogin());

        if (employeeFromDB != null) {
            model.addAttribute("registrationError", "User is already exists!");
            return "registration";
        }

        employee.setActive(true);
        employee.setRoles(Collections.singleton(Role.USER));
        employeeRepository.save(employee);

        return "redirect:/login";
    }
}

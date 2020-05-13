package by.bsuir.coursework.pmacoursework.controller;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import by.bsuir.coursework.pmacoursework.repository.EmployeeRepository;
import by.bsuir.coursework.pmacoursework.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    @Autowired
    public AuthController(EmployeeRepository employeeRepository,
                          EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/registration")
    public String displayRegistrationForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "security/registration";
    }

    @PostMapping(value = "/registration")
    public String registerUser(@RequestParam(name = "passwordConfirmation") String passwordConfirmation,
                               @Valid Employee employee,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors() || bindingResult.hasFieldErrors()) {
            return "security/registration";
        }

        Employee employeeFromDB = employeeRepository.findByLoginOrEmail(employee.getLogin(), employee.getEmail());
        if (employeeFromDB != null) {
            model.addAttribute("registrationError", "This username or email have already been taken!");
            return "security/registration";
        }

        if (employee.getPassword() != null && !employee.getPassword().equals(passwordConfirmation)) {
            model.addAttribute("passwordError", "Passwords are not equal!");
            return "security/registration";
        }

        employeeService.saveEmployee(employee);

        return "redirect:/login";
    }
}

package by.bsuir.coursework.pmacoursework.controller;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import by.bsuir.coursework.pmacoursework.entity.Role;
import by.bsuir.coursework.pmacoursework.repository.EmployeeRepository;
import by.bsuir.coursework.pmacoursework.util.DataExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/employee")
@PreAuthorize(value = "hasAuthority('ADMIN')")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final DataExporter dataExporter;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, DataExporter dataExporter) {
        this.employeeRepository = employeeRepository;
        this.dataExporter = dataExporter;
    }

    @GetMapping(value = "/all")
    public String displayEmployees(@AuthenticationPrincipal Employee employee, Model model) {
        List<Employee> employees = employeeRepository.findAll();

        model.addAttribute("employees", employees);

        return "employees/employees-list";
    }

    @GetMapping(value = "/new")
    public String displayNewEmployeeForm(Model model) {
        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        return "employees/new-employee";
    }

    @PostMapping(value = "/save")
    public String createEmployee(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employees/new-employee";
        }
        employeeRepository.save(employee);

        return "redirect:/employee/all";
    }

    @GetMapping(value = "/edit/{id}")
    public String displayEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id).get();
        List<Role> roles = Arrays.asList(Role.values());

        model.addAttribute("availableRoles", roles);
        model.addAttribute("employee", employee);

        return "employees/employee-edit";
    }

    @PostMapping(value = "/update/{id}")
    public String updateEmployee(@PathVariable @NotNull Long id, Employee updatedEmployee,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            updatedEmployee.setId(id);
            return "employees/employee-edit";
        }

        if (updatedEmployee.getPassword().isEmpty()) {
            updatedEmployee.setPassword(employeeRepository.findById(id).get().getPassword());
        }

        employeeRepository.save(updatedEmployee);

        return "redirect:/employee/all";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);

        return "redirect:/employee/all";
    }

    @PostMapping(value = "/export/{id}/type")
    public String exportEmployeeData(@PathVariable Long id, @RequestParam String type) throws IOException {
        Employee employee = employeeRepository.findById(id).get();

        switch (type.toLowerCase()) {
            case "json":
                dataExporter.jsonExporter(employee);
                break;
            case "xml":
                dataExporter.xmlExporter(employee);
                break;
        }

        return "redirect:/employee/all";
    }
}


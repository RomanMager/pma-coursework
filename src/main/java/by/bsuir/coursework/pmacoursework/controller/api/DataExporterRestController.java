package by.bsuir.coursework.pmacoursework.controller.api;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import by.bsuir.coursework.pmacoursework.repository.EmployeeRepository;
import by.bsuir.coursework.pmacoursework.util.DataExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/employee")
public class DataExporterRestController {

    @Autowired
    private DataExporter dataExporter;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "/principal")
    @ResponseStatus(HttpStatus.OK)
    public void exportPrincipalData(@AuthenticationPrincipal Principal principal) throws IOException {
        dataExporter.jsonExporter((Employee) principal);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee exportEmployeeData(@PathVariable Long id) throws IOException {
        Employee employee = employeeRepository.findById(id).get();
        dataExporter.jsonExporter(employee);

        return employee;
    }
}

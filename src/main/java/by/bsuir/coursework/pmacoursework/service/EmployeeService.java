package by.bsuir.coursework.pmacoursework.service;

import by.bsuir.coursework.pmacoursework.entity.Employee;
import by.bsuir.coursework.pmacoursework.entity.Role;
import by.bsuir.coursework.pmacoursework.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByLogin(username);
    }

    public Employee saveEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setActive(true);
        employee.setRoles(Collections.singleton(Role.USER));
        employeeRepository.save(employee);

        return employee;
    }

    public void updateProfile(Employee original, Employee updatedData) {
        if (!original.getFirstName().equals(updatedData.getFirstName())) {
            original.setFirstName(updatedData.getFirstName());
        }

        if (!original.getLastName().equals(updatedData.getLastName())) {
            original.setLastName(updatedData.getLastName());
        }

        if (!original.getLogin().equals(updatedData.getLogin())) {
            original.setLogin(updatedData.getLogin());
        }

        if (!updatedData.getPassword().isEmpty() && !original.getPassword().equals(updatedData.getPassword())) {
            original.setPassword(passwordEncoder.encode(updatedData.getPassword()));
        }

        if (!original.getEmail().equals(updatedData.getEmail())) {
            original.setEmail(updatedData.getEmail());
        }

        employeeRepository.save(original);
    }
}

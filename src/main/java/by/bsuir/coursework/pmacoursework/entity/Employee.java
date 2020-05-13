package by.bsuir.coursework.pmacoursework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Please provide your login")
    @Size(min = 5, message = "Login must be at least 5 characters long.")
    private String login;

    @Column(nullable = false, unique = true)
    @Email(message = "Please provide a valid email address.")
    @NotEmpty(message = "Please provide an email.")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "Please provide your password")
    @Size(min = 5, message = "Password must be at least 5 characters long.")
    private String password;

    private String firstName;
    private String lastName;

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "User_Role", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToMany(mappedBy = "assignedEmployees")
    private Set<Project> assignedProjects;

    @OneToMany(mappedBy = "assignedTo",
               cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
               fetch = FetchType.LAZY)
    private Set<Task> assignedTasks;

    public void addTask(Task task) {
        assignedTasks.add(task);
        task.setAssignedTo(this);
    }

    public void removeTask(Task task) {
        assignedTasks.remove(task);
        task.setAssignedTo(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                ", assignedProjects=" + assignedProjects +
                ", assignedTasks=" + assignedTasks +
                '}';
    }
}

package by.bsuir.coursework.pmacoursework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
// @Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "project_name")
    @Size(max = 50)
    @NotNull(message = "You must specify project name!")
    private String name;

    @Size(max = 400)
    private String description;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /*
    private Status status;

    private Employee projectLead;

    private Set<Employee> employees;

    private Set<Task> tasks;
    */
}

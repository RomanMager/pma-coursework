package by.bsuir.coursework.pmacoursework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Size(min = 5, max = 60)
    @NotBlank(message = "You must specify project name!")
    @Column(name = "project_name")
    private String name;

    @Size(max = 400)
    private String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Task> tasks;

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

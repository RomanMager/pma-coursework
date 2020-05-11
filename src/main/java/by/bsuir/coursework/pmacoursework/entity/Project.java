package by.bsuir.coursework.pmacoursework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
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

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "status_id")
    private ProjectStatus status;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Task> tasks;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /*
    private Set<Employee> employees;
    */

    @Override
    public int hashCode() {
        return Objects.hash(projectId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId.equals(project.projectId);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

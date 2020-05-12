package by.bsuir.coursework.pmacoursework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Size(min = 5, max = 100)
    @NotNull(message = "You must specify task title!")
    private String title;

    @Size(max = 250)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private TaskType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "task_employee",
               joinColumns = @JoinColumn(name = "task_id"),
               inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Employee assignedTo;

    private boolean isCompleted;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", project=" + project +
                ", isCompleted=" + isCompleted +
                '}';
    }
}

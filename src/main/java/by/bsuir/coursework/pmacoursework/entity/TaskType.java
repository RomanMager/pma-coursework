package by.bsuir.coursework.pmacoursework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Task_Type")
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @NotBlank
    private String title;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskType taskType = (TaskType) o;
        return id.equals(taskType.id);
    }

    @Override
    public String toString() {
        return "TaskType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

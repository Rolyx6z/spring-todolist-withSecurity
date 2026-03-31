package train.todolist.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "todo")
    private String todo;

    @Column(name = "task_date")
    private LocalDate taskDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "group_id")
    private Integer groupId;
}

package train.todolist.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="todo_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="user_id")
    private String userId;

    @Column(name="group_name")
    private String groupName;
}

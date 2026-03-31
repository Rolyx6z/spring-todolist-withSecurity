package train.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import train.todolist.model.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {
    List<Todo> findAllByGroupId(Integer groupId);
    void deleteAllByGroupId(Integer groupId);
}

package train.todolist.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import train.todolist.form.CreateTodoForm;
import train.todolist.model.Todo;
import train.todolist.repository.TodoRepository;

import java.util.List;

@Service
@Transactional
public class TodoService {
    @Autowired
    TodoRepository repository;

    public boolean create(CreateTodoForm createTodoForm,String userId,Integer groupId){
        Todo todo = new Todo();
        todo.setUserId(userId);
        todo.setTodo(createTodoForm.getTodo());
        todo.setTaskDate(createTodoForm.getTaskDate());
        todo.setGroupId(groupId);
        todo.setStatus(0);
        repository.save(todo);
        return true;
    }

    public List<Todo> findAllByGroupId(Integer groupId){
        return repository.findAllByGroupId(groupId);
    }

    public void complete(Integer id){
        repository.deleteById(id);
    }
}

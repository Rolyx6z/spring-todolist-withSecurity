package train.todolist.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import train.todolist.model.Group;
import train.todolist.repository.GroupRepository;
import train.todolist.repository.TodoRepository;

import java.util.List;

@Service
@Transactional
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TodoRepository  todoRepository;

    public List<Group> findAllByUserId(String userId){
        return groupRepository.findAllByUserId(userId);
    }

    public void deleteById(Integer id){
        groupRepository.deleteById(id);
        todoRepository.deleteAllByGroupId(id);
    }

    public boolean create(String userId, String groupName){
        if(groupRepository.existsByUserIdAndGroupName(userId, groupName)){
            return false;
        }
        Group group = new Group();
        group.setUserId(userId);
        group.setGroupName(groupName);
        groupRepository.save(group);

        return true;
    }

    public Group findById(Integer id){
        return groupRepository.findById(id).orElseThrow();
    }
}

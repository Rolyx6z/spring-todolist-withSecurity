package train.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import train.todolist.model.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
    List<Group> findAllByUserId(String UserId);
    boolean existsByUserIdAndGroupName(String userId,String groupName);

}

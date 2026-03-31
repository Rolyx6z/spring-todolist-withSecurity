package train.todolist.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import train.todolist.form.CreateTodoForm;
import train.todolist.form.IdGroupForm;
import train.todolist.form.RegisterGroupForm;
import train.todolist.model.Group;
import train.todolist.model.Todo;
import train.todolist.service.GroupService;
import train.todolist.service.TodoService;

import java.util.List;

public abstract class BaseController {
    @Autowired
    GroupService groupService;

    @Autowired
    TodoService todoService;

    @ModelAttribute
    public void addCommonData(Model model, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {

            String userId = auth.getName(); // ここでようやく名前を取る

            model.addAttribute("groupList", groupService.findAllByUserId(userId));

            Integer groupId = (Integer) session.getAttribute("nowGroupId");

            if (groupId != null) {
                Group selectedGroup = groupService.findById(groupId);
                if (selectedGroup != null) {
                    List<Todo> todoList = todoService.findAllByGroupId(groupId);
                    model.addAttribute("todoList", todoList);
                    model.addAttribute("nowGroupName", selectedGroup.getGroupName());
                } else {
                    session.setAttribute("nowGroupId", null);
                    model.addAttribute("nowGroupName", null);
                    model.addAttribute("todoList", List.of());
                }
            } else {
                model.addAttribute("nowGroupName", null);
                model.addAttribute("todoList", List.of());
            }
        } else {
            model.addAttribute("groupList", List.of());
            model.addAttribute("nowGroupName", null);
        }

        if (!model.containsAttribute("registerGroupForm")) {
            model.addAttribute("registerGroupForm", new RegisterGroupForm());
        }
        if (!model.containsAttribute("idGroupForm")) {
            model.addAttribute("idGroupForm", new IdGroupForm());
        }
        if (!model.containsAttribute("createTodoForm")) {
            model.addAttribute("createTodoForm", new CreateTodoForm());
        }
    }
}

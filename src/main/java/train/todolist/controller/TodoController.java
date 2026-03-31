package train.todolist.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import train.todolist.form.CreateTodoForm;
import train.todolist.model.Todo;
import train.todolist.service.GroupService;
import train.todolist.service.TodoService;

import java.security.Principal;

@Controller
public class TodoController extends BaseController {
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/todo")
    public String showTodo(Model model, HttpSession session){
        System.out.println("--- /todo へのアクセス成功 ---");
        return "todo";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/todo-create")
    public String create(@ModelAttribute @Validated CreateTodoForm createTodoForm, Principal principal, BindingResult result, HttpSession session, Model model){
        String userId = principal.getName();
        Integer nowGroupId = (Integer) session.getAttribute("nowGroupId");

        if(userId == null){
            return "login";
        } else if(nowGroupId == null){
            result.rejectValue("id","error.group","グループを選択してください");

            return "todo";
        } else {
            todoService.create(createTodoForm,userId,nowGroupId);
            return "redirect:/todo";
        }
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/todo-complete")
    public String complete(@ModelAttribute Todo todo, HttpSession session, Model model){
        Integer id = todo.getId();
        todoService.complete(id);

        return "redirect:/todo";
    }
}

package train.todolist.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import train.todolist.form.RegisterGroupForm;
import train.todolist.form.IdGroupForm;
import train.todolist.service.GroupService;

import java.security.Principal;

@Controller
public class GroupController extends BaseController {

    @PreAuthorize("isAuthenticated()")
    @PostMapping("group-delete")
    public String delete(@ModelAttribute @Validated IdGroupForm idGroupForm,BindingResult result,HttpSession session, Model model){
        Integer nowGroupId = (Integer) session.getAttribute("nowGroupId");
        if(nowGroupId != null && nowGroupId.equals(idGroupForm.getId())){
            session.setAttribute("nowGroupId",null);
        }

        if(result.hasErrors()){
            return "todo";
        }
        System.out.println(idGroupForm.getId());
        groupService.deleteById(idGroupForm.getId());
        return "redirect:/todo";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("group-create")
    public String register(@ModelAttribute @Validated RegisterGroupForm registerGroupForm, BindingResult result, Principal principal, HttpSession session, Model model){
        if(result.hasErrors()){
            return "todo";
        }
        boolean isSuccess = groupService.create(principal.getName(), registerGroupForm.getGroupName());
        if(isSuccess){
            return "redirect:/todo";
        } else {
            result.rejectValue("groupName","error.groupName","このグループ名はすでに使用しています");
            registerGroupForm.setGroupName("");
            return "todo";
        }
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("group-select")
    public String select(@ModelAttribute IdGroupForm idGroupForm, BindingResult result, HttpSession session, Model model){
        if(result.hasErrors()){
            return "todo";
        }

        session.setAttribute("nowGroupId", idGroupForm.getId());
        return "redirect:/todo";
    }
}

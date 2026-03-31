package train.todolist.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String LoginForm(){
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/todo";
    }
}

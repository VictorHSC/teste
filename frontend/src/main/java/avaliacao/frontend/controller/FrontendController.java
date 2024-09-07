package avaliacao.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping("/index")
    public String showUserList() {
        return "modulos";
    }
}

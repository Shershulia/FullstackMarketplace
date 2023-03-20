package ntnu.fullstack.marketplace.marketplace.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestMapping;

public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

package org.beny.ignite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginWeb {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}

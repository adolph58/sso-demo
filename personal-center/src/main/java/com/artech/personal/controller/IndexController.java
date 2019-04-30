package com.artech.personal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 引导程序
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, Principal user) {
        model.addAttribute("user", user);
        return "index";
    }
}

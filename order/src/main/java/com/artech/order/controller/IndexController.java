package com.artech.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Created by 程江涛 on 2019/4/29.
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, Principal user) {
        model.addAttribute("user", user);
        return "index";
    }
}

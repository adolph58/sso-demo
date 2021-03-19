package com.artech.order.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 程江涛 on 2019/4/16.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUsername")
    public String getUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

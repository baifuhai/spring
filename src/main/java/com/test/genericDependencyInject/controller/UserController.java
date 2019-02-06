package com.test.genericDependencyInject.controller;

import com.test.genericDependencyInject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    UserService userService;

}

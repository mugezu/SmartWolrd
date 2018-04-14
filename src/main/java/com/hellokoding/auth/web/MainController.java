package com.hellokoding.auth.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Роман on 15.02.2018.
 */
@Controller
public class MainController {
    @RequestMapping(value = {"/main44","/"}, method = RequestMethod.GET)
    public String main44(Model model) {
        return "main";
    }
}

package com.hellokoding.auth.web;

import com.hellokoding.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Роман on 15.02.2018.
 */
@Controller
public class MainController {

    @RequestMapping(value = {"/main44", "/"}, method = RequestMethod.GET)
    public String main44(Model model) {

        return "main";
    }
}

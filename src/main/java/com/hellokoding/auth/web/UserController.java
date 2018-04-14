package com.hellokoding.auth.web;

import com.hellokoding.auth.model.ListOrder;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.service.OrdersService;
import com.hellokoding.auth.service.SecurityService;
import com.hellokoding.auth.service.UserServiceImpl;
import com.hellokoding.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    UserServiceImpl userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private OrdersService ordersService;

    private final String USER_PAGE = "userPage";
    private final String USER_ORDERS = "userOrders";


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/main44";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Неверное имя пользователя или пароль");

        if (logout != null)
            model.addAttribute("message", "Вы успешно вышли из системы");

        return "login";
    }

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String userPage(Model model) throws Exception {
        return USER_PAGE;
    }

    @RequestMapping(value = "/userOrders", method = RequestMethod.GET)
    public String ordersUser(Model model) throws Exception {
        User user = userService.getCurrentUser();

        List<ListOrder> listOrders = ordersService.allOrdersByIdWithSort(user);
        model.addAttribute(USER_ORDERS, listOrders);

        return USER_ORDERS;
    }


}

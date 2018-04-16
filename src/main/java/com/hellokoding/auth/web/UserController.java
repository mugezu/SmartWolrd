package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Orders;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpSession;
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
    private HttpSession httpSession;

    @Autowired
    private OrdersService ordersService;


    private static final String USER_PAGE = "userPage";
    private static final String USER_ORDERS = "userOrders";
    private static final String AMOUNT_ALL_ITEM = "amountAllItem";
    private static final String CURRENT_PAGE = "page";
    private static final Integer AMOUNT_ITEM_PAGE = 5;
    private static final String ITEM_PAGE = "itemPage";
    private static final String USER_INFO = "userInfo";
    private static final String USER = "user";


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) throws Exception {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        httpSession.setAttribute("user", userService.getCurrentUser());

        return "redirect:/main44";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) throws Exception {


        if (error != null)
            model.addAttribute("error", "Неверное имя пользователя или пароль");

        if (logout != null) {
            httpSession.removeAttribute("user");
            model.addAttribute("message", "Вы успешно вышли из системы");
        }
        if (error == null && logout == null) {
            User user = null;
            try {
                user = userService.getCurrentUser();
                httpSession.setAttribute("user", user);
            } catch (AuthenticationException e) {

            }

        }

        return "login";
    }

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String userPage(Model model) throws Exception {
        return USER_PAGE;
    }

    @RequestMapping(value = "/userOrders", method = RequestMethod.GET)
    public String ordersUser(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) throws
            Exception {
        User user = userService.getCurrentUser();

        List<Orders> listOrders = ordersService.allOrdersByIdWithSort(user);

        ;
        model.addAttribute(AMOUNT_ALL_ITEM, listOrders.size());
        model.addAttribute(CURRENT_PAGE, page);
        model.addAttribute(ITEM_PAGE, AMOUNT_ITEM_PAGE);
        model.addAttribute(USER_ORDERS, ordersService.ordersFromPage(page, listOrders, AMOUNT_ITEM_PAGE));

        return USER_ORDERS;
    }

    @RequestMapping(value = {"/userInfo", "/admin/userInfo"}, method = RequestMethod.GET)
    public String userInfo(Model model, @RequestParam(value = "idUser") Long idUser, @ModelAttribute("userForm") User userForm) throws Exception {
        User user = userService.getCurrentUser();
        if (user.getId() == idUser || user.getRole().getName() == "admin") {
            model.addAttribute(USER, userService.findById(idUser));
        }

        return USER_INFO;
    }

    @RequestMapping(value = {"/userInfo", "/admin/userInfo"}, method = RequestMethod.POST)
    public String userInfo(Model model, @ModelAttribute("userForm") User userForm, BindingResult bindingResult) throws Exception {
        userValidator.validateChange(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return USER_INFO;
        }


        return USER_INFO;
    }
}

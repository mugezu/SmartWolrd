package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Orders;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.service.OrdersService;
import com.hellokoding.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpSession;

/**
 * Created by Роман on 26.03.2018.
 */
@Controller
public class BasketController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    UserServiceImpl userService;

    private String BASKET_LIST = "basketList";
    private String BASKET = "basket";


    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model) throws AuthenticationException {
        Orders basketList;
        User user = null;
        try {
            user = userService.getCurrentUser();
        } catch (Exception e) {
        }
        if (user == null) {
            throw new AuthenticationException("Требуется авторизация");
        } else {
            basketList = ordersService.findBasketByIdBayer(user) == null ? new Orders() : ordersService.findBasketByIdBayer(user);
            model.addAttribute(BASKET_LIST, basketList.getSubOrders());
        }
        return BASKET;
    }

    @RequestMapping(value = "/buyBasket", method = RequestMethod.POST)
    public String buyBasket(Model model, @RequestParam(value = "allMoney") Integer allMoney) throws Exception {
        User user = userService.getCurrentUser();
        Orders order = ordersService.findBasketByIdBayer(user);
        ordersService.updateStatusOrder(order.getId(), 1l);

        return "redirect:" + BASKET;
    }
}

package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Basket;
import com.hellokoding.auth.model.ListOrder;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.BasketRepository;
import com.hellokoding.auth.repository.ListOrderRepository;
import com.hellokoding.auth.repository.StatusRepository;
import com.hellokoding.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Роман on 26.03.2018.
 */
@Controller
public class BasketController {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private ListOrderRepository listOrderRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    UserServiceImpl userService;

    private String BASKET_LIST = "basketList";
    private String BASKET = "basket";


    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model) throws AuthenticationException {
        List<Basket> basketList;
        User user = null;
        try {
            user = userService.getCurrentUser();
        } catch (Exception e) {
        }
        if (user == null) {
            throw new AuthenticationException("Требуется авторизация");
        } else {
            basketList = basketRepository.findByIdBayer(user);
            model.addAttribute(BASKET_LIST, basketList);
        }
        return BASKET;
    }

    @RequestMapping(value = "/buyBasket", method = RequestMethod.POST)
    public String buyBasket(Model model, @RequestParam(value = "allMoney") Integer allMoney) throws AuthenticationException {
        List<Basket> basketList;
        User user = null;
        try {
            user = userService.getCurrentUser();
        } catch (Exception e) {
        }
        if (user == null) {
            throw new AuthenticationException("Требуется авторизация");
        } else {
            basketList = basketRepository.findByIdBayer(user);
            Long maxIdOrder = listOrderRepository.findOneMaxByIdOrder();
            for (Basket basket : basketList) {
                ListOrder order = new ListOrder();
                order.setAmount(basket.getAmount());
                order.setIdBayer(user);
                order.setIdItem(basket.getIdItem());
                order.setPrice(allMoney);
                order.setDate(new Date());
                order.setStatus(statusRepository.findOne(1l));
                order.setIdOrder(1 + maxIdOrder);
                listOrderRepository.save(order);
                basketRepository.delete(basket);
            }
        }
        return "redirect:" + BASKET;
    }
}

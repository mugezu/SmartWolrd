package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Catalog;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.service.CatalogService;
import com.hellokoding.auth.service.OrdersService;
import com.hellokoding.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Роман on 18.03.2018.
 */
@Controller
public class ItemController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    CatalogService catalogService;



    private final String ITEM = "item";

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public String showItem(Model model, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute(ITEM, catalogService.findItem(id));
        return ITEM;
    }

    @RequestMapping(value = "/buyItem", method = RequestMethod.GET)
    public String buyItem(Model model, @RequestParam(value = "id", required = false) Long id, @RequestParam(value = "amount", required = false,defaultValue = "1") Integer amount) throws Exception {
        Catalog item = catalogService.findItem(id);
        model.addAttribute(ITEM, item);

        User user = userService.getCurrentUser();

        ordersService.addItemFromBasket(user, id, amount);
        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
    public String deleteItem(Model model, @RequestParam(value = "id", required = false) Long id) throws Exception {

        Catalog item = catalogService.findItem(id);
        User user = userService.getCurrentUser();
        ordersService.deleteItemFromBasketUser(item, user);

        return "redirect:" + request.getHeader("referer");
    }
}

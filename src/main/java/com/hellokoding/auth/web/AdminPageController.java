package com.hellokoding.auth.web;

import com.hellokoding.auth.model.Catalog;
import com.hellokoding.auth.model.Orders;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.StatusRepository;
import com.hellokoding.auth.service.CatalogService;
import com.hellokoding.auth.service.OrdersService;
import com.hellokoding.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Роман on 02.04.2018.
 */
@Controller
public class AdminPageController {

    @Autowired
    OrdersService ordersService;
    @Autowired
    CatalogService catalogService;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    HttpServletRequest request;
    @Autowired
    UserServiceImpl userService;


    private final String ADD_ITEM = "addItem";
    private final String ADMIN_PAGE = "adminPage";
    private final String ADMIN_ORDERS = "adminOrders";
    private final String ITEM = "item";
    private final String ORDERS = "orders";
    private final String USERS = "users";
    private final String LIST_STATUS = "listStatus";
    private final String AMOUNT_ALL_ITEM = "amountAllItem";
    private final String CURRENT_PAGE = "page";
    private final Integer AMOUNT_ITEM_PAGE = 5;
    private final String ITEM_PAGE = "itemPage";
    private final String ID_STATUS = "idStatus";
    private static final String USERS_LIST = "usersList";


    @RequestMapping(value = "/admin/adminPage", method = RequestMethod.GET)
    public String adminPage(Model model) {
        return ADMIN_PAGE;
    }


    @RequestMapping(value = "/admin/updateStatusOrder", method = RequestMethod.GET)
    public String updateStatusOrder(Model model, @RequestParam(value = "id") Long id, @RequestParam(value = "status") Long status) throws AuthenticationException {
        ordersService.updateStatusOrder(id, status);
        return "redirect:" + request.getHeader("referer");
    }


    @RequestMapping(value = "/admin/addItem", method = RequestMethod.GET)
    public String showCatalog(Model model, @ModelAttribute("catalog") Catalog catalog, @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            Catalog item = catalogService.findItem(id);
            model.addAttribute(ITEM, item);
        }
        return ADD_ITEM;
    }

    @RequestMapping(value = "/admin/addItem", method = RequestMethod.POST)
    public String addItem(@ModelAttribute("catalog") Catalog catalog, Model model) throws IOException {
        catalogService.addItem(catalog);
        model.addAttribute("message", "Товар добавлен/изменен");

        return adminPage(model);
        //return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping(value = "/admin/deleteItemDB", method = RequestMethod.POST)
    public String deleteItemDB(@RequestParam(value = "id", required = false) Long id, Model model) throws IOException {
        catalogService.deleteItem(id);
        model.addAttribute("message", "Товар удален");
        return adminPage(model);
    }


    @RequestMapping(value = "/admin/adminOrders", method = RequestMethod.GET)
    public String adminOrders(Model model, @RequestParam(value = "idStatus", required = false) Long idStatus, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "idOrder", required = false) Long idOrder) {
        List<Orders> listOrders = new LinkedList<>();
        ;
        if (idOrder == null) {
            if (idStatus == null)
                listOrders = ordersService.allOrdersWithSort();
            else {
                listOrders = ordersService.findByStatusId(idStatus);
                model.addAttribute(ID_STATUS, idStatus);
            }
        } else {
            listOrders.add(ordersService.findById(idOrder));
        }
        model.addAttribute(ORDERS, ordersService.ordersFromPage(page, listOrders, AMOUNT_ITEM_PAGE));
        model.addAttribute(AMOUNT_ALL_ITEM, listOrders.size());
        model.addAttribute(LIST_STATUS, statusRepository.findAll());
        model.addAttribute(CURRENT_PAGE, page);
        model.addAttribute(ITEM_PAGE, AMOUNT_ITEM_PAGE);
        return ADMIN_ORDERS;
    }

    @RequestMapping(value = "/admin/usersList", method = RequestMethod.GET)
    public String usersList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        List<User> listUsers = userService.findAll();

        model.addAttribute(USERS, userService.usersFromPage(page, listUsers, AMOUNT_ITEM_PAGE));
        model.addAttribute(AMOUNT_ALL_ITEM, listUsers.size());
        model.addAttribute(LIST_STATUS, statusRepository.findAll());
        model.addAttribute(CURRENT_PAGE, page);
        model.addAttribute(ITEM_PAGE, AMOUNT_ITEM_PAGE);

        return USERS_LIST;
    }
}

package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Catalog;
import com.hellokoding.auth.model.Orders;
import com.hellokoding.auth.model.SubOrders;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.OrdersRepository;
import com.hellokoding.auth.repository.StatusRepository;
import com.hellokoding.auth.repository.SubOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Роман on 08.04.2018.
 */
@Service
public class OrdersService {
    @Autowired
    OrdersRepository orderRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    private SubOrdersRepository subOrdersRepository;
    @Autowired
    private CatalogService catalogService;

    public void updateStatusOrder(Long idOrder, Long idStatus) {
        Orders order = orderRepository.findOne(idOrder);
        order.setStatus(statusRepository.findOne(idStatus));
        orderRepository.saveAndFlush(order);

    }

    public List<Orders> allOrdersWithSort() {
        return orderRepository.findAllSort();
    }

    public List<Orders> allOrdersByIdWithSort(User user) {
        return orderRepository.findAllByIdSort(user);
    }

    public List<Orders> ordersFromPage(Integer page, List<Orders> listOrders, Integer amount) {
        List<Orders> result = new ArrayList<>();
        try {
            for (int i = (page - 1) * amount; i < page * amount; i++) {
                result.add(listOrders.get(i));
            }
        } finally {
            return result;
        }
    }

    public void saveOrder(User user, Integer money) {
        Orders order = new Orders();
        order.setIdBayer(user);
        order.setPrice(money);
        order.setDate(new Date());
        order.setStatus(statusRepository.findOne(1l));
        orderRepository.save(order);
    }


    public Orders findOrderMaxId() {
        return orderRepository.findOne(orderRepository.findByIdMax());
    }

    public Orders findById(Long idOrder) {
        return orderRepository.findOne(idOrder);
    }

    public List<Orders> findByStatusId(Long idStatus) {
        return orderRepository.findByStatus(statusRepository.findOne(idStatus));
    }

    public Orders findBasketByIdBayer(User user) {
        return orderRepository.findBasket(user, statusRepository.findOne(6l));
    }

    public void addItemFromBasket(User user, Long idItem, Integer amount) {
        Catalog item = catalogService.findItem(idItem);
        Orders order = findBasketByIdBayer(user);
        if (order == null) {
            order = new Orders();
            order.setIdBayer(user);
            order.setStatus(statusRepository.findOne(6l));

        }
        order.setPrice(order.getPrice() + item.getPrice() * amount);
        order.setDate(new Date());
        orderRepository.save(order);
        SubOrders subOrders = subOrdersRepository.findByIdItemAndOrders(item, order);
        if (subOrders == null) {
            subOrders = new SubOrders();
            subOrders.setIdItem(item);
            subOrders.setOrders(order);
        }
        subOrders.setAmount(amount);
        subOrders.setPrice(item.getPrice() * amount);

        subOrdersRepository.save(subOrders);
    }

    public void deleteItemFromBasketUser(Catalog item, User user) {
        Orders order = findBasketByIdBayer(user);
        SubOrders subOrders = subOrdersRepository.findByIdItemAndOrders(item, order);
        order.setPrice(order.getPrice() - subOrders.getPrice());
        orderRepository.saveAndFlush(order);
        subOrdersRepository.delete(subOrders);
    }
}

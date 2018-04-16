package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Basket;
import com.hellokoding.auth.model.Orders;
import com.hellokoding.auth.model.SubOrders;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.OrdersRepository;
import com.hellokoding.auth.repository.StatusRepository;
import com.hellokoding.auth.repository.SubOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void saveSubOrders(List<Basket> basketList, Orders orders) {
        Set<SubOrders> subOrdersSet = new HashSet<>();

        for (Basket basket : basketList) {
            SubOrders subOrders = new SubOrders();
            subOrders.setIdItem(basket.getIdItem());
            subOrders.setAmount(basket.getAmount());
            subOrders.setPrice(basket.getPrice());
            subOrders.setOrders(orders);
            subOrdersSet.add(subOrders);
        }
        subOrdersRepository.save(subOrdersSet);
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
}

package com.hellokoding.auth.service;

import com.hellokoding.auth.model.ListOrder;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.ListOrderRepository;
import com.hellokoding.auth.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Роман on 08.04.2018.
 */
@Service
public class OrdersService {
    @Autowired
    ListOrderRepository orderRepository;

    @Autowired
    StatusRepository statusRepository;

    public void updateStatusOrder(Long idOrder, Long idStatus) {
        List<ListOrder> listOrders = orderRepository.findByIdOrder(idOrder);

        for (ListOrder order : listOrders) {
            order.setStatus(statusRepository.findOne(idStatus));
            orderRepository.saveAndFlush(order);
        }
    }

    public List<ListOrder> allOrdersWithSort() {
        return orderRepository.findAllSort();
    }

    public List<ListOrder> allOrdersByIdWithSort(User user) {
        return orderRepository.findAllByIdSort(user);
    }

    public List<ListOrder> ordersFromPage(Integer page, List<ListOrder> listOrders) {
        List<ListOrder> result = new ArrayList<>();
        return result;
    }

}

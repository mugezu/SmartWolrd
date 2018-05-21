package com.hellokoding.auth.repository;


import com.hellokoding.auth.model.Catalog;
import com.hellokoding.auth.model.Orders;
import com.hellokoding.auth.model.SubOrders;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Роман on 15.04.2018.
 */
public interface SubOrdersRepository extends JpaRepository<SubOrders, Long> {

    SubOrders findByIdItemAndOrders(Catalog item,Orders order);

}


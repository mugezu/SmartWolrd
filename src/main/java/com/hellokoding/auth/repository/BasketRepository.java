package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Basket;
import com.hellokoding.auth.model.Catalog;
import com.hellokoding.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Роман on 22.03.2018.
 */
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket save(Basket basket);

    Basket saveAndFlush(Basket basket);

    Basket findByIdBayerAndIdItem(User user, Catalog catalog);

    List<Basket> findByIdBayer(User user);

}

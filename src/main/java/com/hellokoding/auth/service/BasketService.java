package com.hellokoding.auth.service;

import com.hellokoding.auth.model.Basket;
import com.hellokoding.auth.model.Catalog;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Роман on 08.04.2018.
 */
@Service
public class BasketService {
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    CatalogService catalogService;

    public void deleteItemFromBasketUser(Catalog item, User user) {
        Basket basket = basketRepository.findByIdBayerAndIdItem(user, item);
        basketRepository.delete(basket);
    }

    public void addItemFromBasket(User user, Long idItem, Integer amount) {
        Catalog item = catalogService.findItem(idItem);
        Basket basket = basketRepository.findByIdBayerAndIdItem(user, item);
        if (basket != null) {
            if (amount != null) {
                basket.setAmount(amount);
            } else {
                basket.setAmount(basket.getAmount() + 1);
            }
            basketRepository.saveAndFlush(basket);
        } else {
            basket = new Basket();
            basket.setPrice(item.getPrice());
            basket.setIdBayer(user);
            basket.setIdItem(item);
            basket.setAmount(1);
            basketRepository.save(basket);

        }
    }

}

package com.hellokoding.auth.model;

import javax.persistence.*;

/**
 * Created by Роман on 14.04.2018.
 */
@Entity
@Table(name = "sub_orders")

public class SubOrders {
    private Long id;
    private Catalog idItem;
    private Integer amount;
    private Integer price;
    private Orders orders;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdItem(Catalog idItem) {
        this.idItem = idItem;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    @ManyToOne
    @JoinColumn(name = "id_order")
    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @ManyToOne
    @JoinColumn(name = "id_item")
    public Catalog getIdItem() {
        return idItem;
    }

}

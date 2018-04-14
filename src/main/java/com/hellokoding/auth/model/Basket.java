package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Роман on 14.02.2018.
 */
@Entity
@Table(name = "basket")
public class Basket {
    private Long id;
    private User idBayer;
    private Catalog idItem;
    private Integer amount;
    private Integer price;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basket)) return false;
        Basket basket = (Basket) o;
        return Objects.equals(id, basket.id) &&
                Objects.equals(idBayer, basket.idBayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idBayer);
    }

    @ManyToOne
    @JoinColumn(name = "id_bayer")
    public User getIdBayer() {
        return idBayer;
    }

    public void setIdBayer(User idBayer) {
        this.idBayer = idBayer;
    }

    @ManyToOne
    @JoinColumn(name = "id_item")
    public Catalog getIdItem() {
        return idItem;
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

}



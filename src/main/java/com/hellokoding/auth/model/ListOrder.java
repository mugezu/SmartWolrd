package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Роман on 14.02.2018.
 */
@Entity
@Table(name = "list_order")
public class ListOrder {
    private Long id;
    private Long idOrder;
    private User idBayer;
    private Catalog idItem;
    private Integer amount;
    private Integer price;
    private Date date;
    private Status status;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @ManyToOne
    @JoinColumn(name = "status")
    public Status getStatus() {
            return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

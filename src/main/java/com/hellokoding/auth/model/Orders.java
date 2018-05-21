package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Роман on 14.02.2018.
 */
@Entity
@Table(name = "orders")
public class Orders {
    private Long id;
    private User idBayer;
    private Integer price = 0;
    private Date date;
    private Status status;
    private Set<SubOrders> subOrders = new HashSet<>();


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @ManyToOne
    @JoinColumn(name = "id_bayer")
    public User getIdBayer() {
        return idBayer;
    }

    public void setIdBayer(User idBayer) {
        this.idBayer = idBayer;
    }


    @Column(name="price", columnDefinition="Decimal(10,2) default '0'")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price == null ? 0 : price;
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

    @OneToMany(mappedBy = "orders")
    public Set<SubOrders> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(Set<SubOrders> subOrders) {
        this.subOrders = subOrders;
    }
}

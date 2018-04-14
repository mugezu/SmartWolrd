package com.hellokoding.auth.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Роман on 05.04.2018.
 */
@Entity
@Table(name = "status")
public class Status {
    private Long id;
    private String status;
    private Set<ListOrder> orders = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "status")
    public Set<ListOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<ListOrder> orders) {
        this.orders = orders;
    }
}

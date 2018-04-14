package com.hellokoding.auth.model;

import com.hellokoding.auth.Other.Filters;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private String number;
    private String address;
    private String numberCard;
    private Role role;
    private Set<Basket> baskets = new HashSet<Basket>();
    private Set<ListOrder> listOrders = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @ManyToOne
    @JoinColumn(name = "id_role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "idBayer")
    public Set<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(Set<Basket> baskets) {
        this.baskets = baskets;
    }

    @OneToMany(mappedBy = "idBayer")
    public Set<ListOrder> getListOrders() {
        return listOrders;
    }

    public void setListOrders(Set<ListOrder> listOrders) {
        this.listOrders = listOrders;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NumberFormat
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }
}

package com.hellokoding.auth.model;

import javax.persistence.*;

/**
 * Created by Роман on 19.05.2018.
 */
@Entity
@Table(name = "rating")
public class Rating {
    private Long id;
    private User idBayer;
    private Catalog idItem;
    private Grade idGrade;

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

    @ManyToOne
    @JoinColumn(name = "id_item")
    public Catalog getIdItem() {
        return idItem;
    }

    public void setIdItem(Catalog idItem) {
        this.idItem = idItem;
    }

    @ManyToOne
    @JoinColumn(name = "id_grade")
    public Grade getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(Grade idGrade) {
        this.idGrade = idGrade;
    }
}

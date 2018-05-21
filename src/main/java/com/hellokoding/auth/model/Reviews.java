package com.hellokoding.auth.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Роман on 19.05.2018.
 */
@Entity
@Table(name = "reviews")
public class Reviews {
    private Long id;
    private String liked;
    private String notLike;
    private String comment;
    private Date date = new Date();
    private User idBayer;
    private Catalog idItem;
    private StatusReviews statusReviews = new StatusReviews(1l);

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


    @ManyToOne
    @JoinColumn(name = "id_status")
    public StatusReviews getStatusReviews() {
        return statusReviews;
    }

    public void setStatusReviews(StatusReviews statusReviews) {
        this.statusReviews = statusReviews;
    }

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

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public String getNotLike() {
        return notLike;
    }

    public void setNotLike(String notLike) {
        this.notLike = notLike;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}

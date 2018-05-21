package com.hellokoding.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Роман on 19.05.2018.
 */
@Entity
@Table(name = "status_reviews")
public class StatusReviews {
    private Long id;
    @JsonIgnore
    private Set<Reviews> reviews = new HashSet<>();
    private String desctiption;

    public StatusReviews() {
    }

    public StatusReviews(long id) {
        this.id=id;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "statusReviews")
    public Set<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Reviews> reviews) {
        this.reviews = reviews;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }
}

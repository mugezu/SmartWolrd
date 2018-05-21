package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Catalog;
import com.hellokoding.auth.model.Reviews;
import com.hellokoding.auth.model.User;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Роман on 20.05.2018.
 */
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    Reviews findById(Long id);
    }

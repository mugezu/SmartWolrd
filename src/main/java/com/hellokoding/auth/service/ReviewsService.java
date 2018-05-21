package com.hellokoding.auth.service;

import com.hellokoding.auth.Other.Pages;
import com.hellokoding.auth.model.Catalog;
import com.hellokoding.auth.model.Reviews;
import com.hellokoding.auth.repository.ReviewsPaging;
import com.hellokoding.auth.repository.ReviewsRepository;
import com.hellokoding.auth.repository.StatusReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Роман on 21.05.2018.
 */
@Service
public class ReviewsService {
    @Autowired
    StatusReviewsRepository statusReviewsRepository;
    @Autowired
    ReviewsRepository reviewsRepository;
    @Autowired
    ReviewsPaging reviewsPaging;


    public List<Reviews> findReviewsByItemOK(Catalog item, Pages pages) {
        return reviewsPaging.findByIdItemAndStatusReviews(item, statusReviewsRepository.findOne(2l), pages);
    }
}

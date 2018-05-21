package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.Catalog;
import com.hellokoding.auth.model.Reviews;
import com.hellokoding.auth.model.StatusReviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Роман on 20.05.2018.
 */
public interface ReviewsPaging extends PagingAndSortingRepository<Reviews, Long> {
    List<Reviews> findByIdItemAndStatusReviews(Catalog catalog, StatusReviews statusReviews, Pageable pageable);
}

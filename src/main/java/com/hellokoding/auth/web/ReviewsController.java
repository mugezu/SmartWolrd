package com.hellokoding.auth.web;

import com.hellokoding.auth.Other.Pages;
import com.hellokoding.auth.model.Reviews;
import com.hellokoding.auth.service.CatalogService;
import com.hellokoding.auth.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Роман on 20.05.2018.
 */
@Controller
public class ReviewsController {
    @Autowired
    CatalogService catalogService;

    @Autowired
    ReviewsService reviewsService;

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Reviews> getMyData(@RequestParam(required = false, defaultValue = "1") final Integer page,
                                       @RequestParam final Long id) throws Exception {
        List<Reviews> reviews = reviewsService.findReviewsByItemOK(catalogService.findItem(id), new Pages(page, 4, null));
        return reviews;
    }

    @RequestMapping(value = "/reviews/sendReview", method = RequestMethod.POST)
    public void sendReviews(@RequestParam String json) {
        System.out.println(json);

    }

}

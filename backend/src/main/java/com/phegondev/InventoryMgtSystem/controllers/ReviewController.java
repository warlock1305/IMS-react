package com.phegondev.InventoryMgtSystem.controllers;


import com.phegondev.InventoryMgtSystem.dtos.ReviewDTO;
import com.phegondev.InventoryMgtSystem.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/restaurant/{restaurantId}")
    public List<ReviewDTO> getReviewsByRestaurant(@PathVariable Long restaurantId) {
        return reviewService.getReviewsByRestaurant(restaurantId);
    }

    @PostMapping("/restaurant/{restaurantId}")
    public ReviewDTO addReviewToRestaurant(@PathVariable Long restaurantId, @RequestBody ReviewDTO reviewDTO) {
        return reviewService.addReviewToRestaurant(restaurantId, reviewDTO);
    }

    @GetMapping("/attraction/{attractionId}")
    public List<ReviewDTO> getReviewsByAttraction(@PathVariable Long attractionId) {
        return reviewService.getReviewsByAttraction(attractionId);
    }

    @PostMapping("/attraction/{attractionId}")
    public ReviewDTO addReviewToAttraction(@PathVariable Long attractionId, @RequestBody ReviewDTO reviewDTO) {
        return reviewService.addReviewToAttraction(attractionId, reviewDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}

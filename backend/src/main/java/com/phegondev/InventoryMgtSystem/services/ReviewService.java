package com.phegondev.InventoryMgtSystem.services;


import com.phegondev.InventoryMgtSystem.dtos.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getReviewsByRestaurant(Long restaurantId);
    ReviewDTO addReviewToRestaurant(Long restaurantId, ReviewDTO reviewDTO);
    List<ReviewDTO> getReviewsByAttraction(Long attractionId);
    ReviewDTO addReviewToAttraction(Long attractionId, ReviewDTO reviewDTO);
    void deleteReview(Long id);
}

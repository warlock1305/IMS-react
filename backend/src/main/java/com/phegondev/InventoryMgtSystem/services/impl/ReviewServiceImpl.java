package com.phegondev.InventoryMgtSystem.services.impl;


import com.phegondev.InventoryMgtSystem.dtos.ReviewDTO;
import com.phegondev.InventoryMgtSystem.models.Attraction;
import com.phegondev.InventoryMgtSystem.models.Restaurant;
import com.phegondev.InventoryMgtSystem.models.Review;
import com.phegondev.InventoryMgtSystem.repositories.AttractionRepository;
import com.phegondev.InventoryMgtSystem.repositories.RestaurantRepository;
import com.phegondev.InventoryMgtSystem.repositories.ReviewRepository;
import com.phegondev.InventoryMgtSystem.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final AttractionRepository attractionRepository;

    @Override
    public List<ReviewDTO> getReviewsByRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + restaurantId));

        return restaurant.getReviews()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO addReviewToRestaurant(Long restaurantId, ReviewDTO reviewDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + restaurantId));

        Review review = Review.builder()
                .username(reviewDTO.getUsername())
                .date(reviewDTO.getDate())
                .rating(reviewDTO.getRating())
                .review(reviewDTO.getReview())
                .restaurant(restaurant)
                .build();

        return convertToDTO(reviewRepository.save(review));
    }

    @Override
    public List<ReviewDTO> getReviewsByAttraction(Long attractionId) {
        Attraction attraction = attractionRepository.findById(attractionId)
                .orElseThrow(() -> new RuntimeException("Attraction not found with id " + attractionId));

        return attraction.getReviews()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO addReviewToAttraction(Long attractionId, ReviewDTO reviewDTO) {
        Attraction attraction = attractionRepository.findById(attractionId)
                .orElseThrow(() -> new RuntimeException("Attraction not found with id " + attractionId));

        Review review = Review.builder()
                .username(reviewDTO.getUsername())
                .date(reviewDTO.getDate())
                .rating(reviewDTO.getRating())
                .review(reviewDTO.getReview())
                .attraction(attraction)
                .build();

        return convertToDTO(reviewRepository.save(review));
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    private ReviewDTO convertToDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .username(review.getUsername())
                .date(review.getDate())
                .rating(review.getRating())
                .review(review.getReview())
                .build();
    }
}

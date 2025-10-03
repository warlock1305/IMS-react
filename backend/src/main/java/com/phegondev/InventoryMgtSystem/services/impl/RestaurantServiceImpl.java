package com.phegondev.InventoryMgtSystem.services.impl;


import com.phegondev.InventoryMgtSystem.dtos.RestaurantDTO;
import com.phegondev.InventoryMgtSystem.dtos.ReviewDTO;
import com.phegondev.InventoryMgtSystem.models.Restaurant;
import com.phegondev.InventoryMgtSystem.models.Review;
import com.phegondev.InventoryMgtSystem.repositories.RestaurantRepository;
import com.phegondev.InventoryMgtSystem.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + id));
    }

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = convertToEntity(restaurantDTO);
        return convertToDTO(restaurantRepository.save(restaurant));
    }

    @Override
    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id " + id));

        // update fields
        restaurant.setName(restaurantDTO.getName());
        restaurant.setImages(restaurantDTO.getImages());
        restaurant.setRating(restaurantDTO.getRating());
        restaurant.setReviewCount(restaurantDTO.getReviewCount());
        restaurant.setPriceLevel(restaurantDTO.getPriceLevel());
        restaurant.setCuisine(restaurantDTO.getCuisine());
        restaurant.setOpeningHours(restaurantDTO.getOpeningHours());
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setPhone(restaurantDTO.getPhone());
        restaurant.setWebsite(restaurantDTO.getWebsite());
        restaurant.setWeeklyHours(restaurantDTO.getWeeklyHours());

        return convertToDTO(restaurantRepository.save(restaurant));
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    private RestaurantDTO convertToDTO(Restaurant restaurant) {
        List<ReviewDTO> reviewDTOs = restaurant.getReviews() != null
                ? restaurant.getReviews().stream().map(this::convertReviewToDTO).collect(Collectors.toList())
                : null;

        return RestaurantDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .images(restaurant.getImages())
                .rating(restaurant.getRating())
                .reviewCount(restaurant.getReviewCount())
                .priceLevel(restaurant.getPriceLevel())
                .cuisine(restaurant.getCuisine())
                .openingHours(restaurant.getOpeningHours())
                .description(restaurant.getDescription())
                .address(restaurant.getAddress())
                .phone(restaurant.getPhone())
                .website(restaurant.getWebsite())
                .weeklyHours(restaurant.getWeeklyHours())
                .reviews(reviewDTOs)
                .build();
    }

    private Restaurant convertToEntity(RestaurantDTO dto) {
        Restaurant restaurant = Restaurant.builder()
                .id(dto.getId())
                .name(dto.getName())
                .images(dto.getImages())
                .rating(dto.getRating())
                .reviewCount(dto.getReviewCount())
                .priceLevel(dto.getPriceLevel())
                .cuisine(dto.getCuisine())
                .openingHours(dto.getOpeningHours())
                .description(dto.getDescription())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .website(dto.getWebsite())
                .weeklyHours(dto.getWeeklyHours())
                .build();

        return restaurant;
    }

    private ReviewDTO convertReviewToDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .username(review.getUsername())
                .date(review.getDate())
                .rating(review.getRating())
                .review(review.getReview())
                .build();
    }
}

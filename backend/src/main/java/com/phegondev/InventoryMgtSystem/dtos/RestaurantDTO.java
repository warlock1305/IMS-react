package com.phegondev.InventoryMgtSystem.dtos;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDTO {
    private Long id;
    private String name;
    private List<String> images;
    private Double rating;
    private Integer reviewCount;
    private String priceLevel;
    private String cuisine;
    private String openingHours;
    private String description;
    private String address;
    private String phone;
    private String website;
    private String weeklyHours; // JSON text
    private List<ReviewDTO> reviews;
}

package com.phegondev.InventoryMgtSystem.dtos;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttractionDTO {
    private Long id;
    private String name;
    private String subName;
    private List<String> images;
    private Double rating;
    private Integer reviewCount;
    private List<String> tracks;
    private String description;
    private List<Long> nearLocationIds;
    private List<Long> recommendedLocationIds;
    private List<ReviewDTO> reviews;
}

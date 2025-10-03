package com.phegondev.InventoryMgtSystem.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
    private Long id;
    private String username;
    private String date;
    private Double rating;
    private String review;
}

package com.phegondev.InventoryMgtSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "attractions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Attraction name is required")
    private String name;

    /** Optional alternate/sub name */
    private String subName;

    /** List of image URLs */
    @ElementCollection
    @CollectionTable(name = "attraction_images", joinColumns = @JoinColumn(name = "attraction_id"))
    @Column(name = "image_url")
    private List<String> images;

    @DecimalMin(value = "0.0", inclusive = true, message = "Rating cannot be negative")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
    private Double rating;

    @Min(value = 0, message = "Review count cannot be negative")
    private Integer reviewCount;

    /** Track names like guided audio topics */
    @ElementCollection
    @CollectionTable(name = "attraction_tracks", joinColumns = @JoinColumn(name = "attraction_id"))
    @Column(name = "track_name")
    private List<String> tracks;

    @Column(length = 4000)
    private String description;

    /** Nearby location IDs (can be restaurants or other attractions) */
    @ElementCollection
    @CollectionTable(name = "attraction_near_location_ids", joinColumns = @JoinColumn(name = "attraction_id"))
    @Column(name = "near_location_id")
    private List<Long> nearLocationIds;

    /** Recommended location IDs (references to other attractions) */
    @ElementCollection
    @CollectionTable(name = "attraction_recommended_ids", joinColumns = @JoinColumn(name = "attraction_id"))
    @Column(name = "recommended_id")
    private List<Long> recommendedLocationIds;

    /** Reviews */
    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviews;
}



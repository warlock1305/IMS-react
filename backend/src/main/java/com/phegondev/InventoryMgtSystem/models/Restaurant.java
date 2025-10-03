package com.phegondev.InventoryMgtSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Restaurant name is required")
    private String name;

    @ElementCollection
    @CollectionTable(name = "restaurant_images", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "image_url")
    private List<String> images;

    @DecimalMin(value = "0.0", inclusive = true, message = "Rating cannot be negative")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
    private Double rating;

    @Min(value = 0, message = "Review count cannot be negative")
    private Integer reviewCount;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Review> reviews;

    @NotBlank(message = "Price level is required")
    private String priceLevel;

    @NotBlank(message = "Cuisine information is required")
    private String cuisine;

    @NotBlank(message = "Opening hours are required")
    private String openingHours;

    @Column(length = 2000)
    private String description;

    @NotBlank(message = "Address is required")
    private String address;

    private String phone;

    private String website;

    /**
     * Weekly hours kept as JSON string for flexibility
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String weeklyHours;

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", images=" + images +
                ", rating=" + rating +
                ", reviewCount=" + reviewCount +
                ", priceLevel='" + priceLevel + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", openingHours='" + openingHours + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", weeklyHours='" + weeklyHours + '\'' +
                '}';
    }
}

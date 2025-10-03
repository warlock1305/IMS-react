package com.phegondev.InventoryMgtSystem.services.impl;


import com.phegondev.InventoryMgtSystem.dtos.AttractionDTO;
import com.phegondev.InventoryMgtSystem.dtos.ReviewDTO;
import com.phegondev.InventoryMgtSystem.models.Attraction;
import com.phegondev.InventoryMgtSystem.models.Review;
import com.phegondev.InventoryMgtSystem.repositories.AttractionRepository;
import com.phegondev.InventoryMgtSystem.services.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;

    @Override
    public List<AttractionDTO> getAllAttractions() {
        return attractionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AttractionDTO getAttractionById(Long id) {
        return attractionRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Attraction not found with id " + id));
    }

    @Override
    public AttractionDTO createAttraction(AttractionDTO attractionDTO) {
        Attraction attraction = convertToEntity(attractionDTO);
        return convertToDTO(attractionRepository.save(attraction));
    }

    @Override
    public AttractionDTO updateAttraction(Long id, AttractionDTO attractionDTO) {
        Attraction attraction = attractionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attraction not found with id " + id));

        // Update fields
        attraction.setName(attractionDTO.getName());
        attraction.setSubName(attractionDTO.getSubName());
        attraction.setImages(attractionDTO.getImages());
        attraction.setRating(attractionDTO.getRating());
        attraction.setReviewCount(attractionDTO.getReviewCount());
        attraction.setTracks(attractionDTO.getTracks());
        attraction.setDescription(attractionDTO.getDescription());
        attraction.setNearLocationIds(attractionDTO.getNearLocationIds());
        attraction.setRecommendedLocationIds(attractionDTO.getRecommendedLocationIds());

        return convertToDTO(attractionRepository.save(attraction));
    }

    @Override
    public void deleteAttraction(Long id) {
        attractionRepository.deleteById(id);
    }

    private AttractionDTO convertToDTO(Attraction attraction) {
        List<ReviewDTO> reviewDTOs = attraction.getReviews() != null
                ? attraction.getReviews().stream().map(this::convertReviewToDTO).collect(Collectors.toList())
                : null;

        return AttractionDTO.builder()
                .id(attraction.getId())
                .name(attraction.getName())
                .subName(attraction.getSubName())
                .images(attraction.getImages())
                .rating(attraction.getRating())
                .reviewCount(attraction.getReviewCount())
                .tracks(attraction.getTracks())
                .description(attraction.getDescription())
                .nearLocationIds(attraction.getNearLocationIds())
                .recommendedLocationIds(attraction.getRecommendedLocationIds())
                .reviews(reviewDTOs)
                .build();
    }

    private Attraction convertToEntity(AttractionDTO dto) {
        Attraction attraction = Attraction.builder()
                .id(dto.getId())
                .name(dto.getName())
                .subName(dto.getSubName())
                .images(dto.getImages())
                .rating(dto.getRating())
                .reviewCount(dto.getReviewCount())
                .tracks(dto.getTracks())
                .description(dto.getDescription())
                .nearLocationIds(dto.getNearLocationIds())
                .recommendedLocationIds(dto.getRecommendedLocationIds())
                .build();

        return attraction;
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

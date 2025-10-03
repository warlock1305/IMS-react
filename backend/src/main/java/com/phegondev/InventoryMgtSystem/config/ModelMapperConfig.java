package com.phegondev.InventoryMgtSystem.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import com.phegondev.InventoryMgtSystem.models.User;
import com.phegondev.InventoryMgtSystem.models.Category;
import com.phegondev.InventoryMgtSystem.models.Transaction;
import com.phegondev.InventoryMgtSystem.models.Restaurant;
import com.phegondev.InventoryMgtSystem.models.Attraction;
import com.phegondev.InventoryMgtSystem.models.Review;
import com.phegondev.InventoryMgtSystem.dtos.UserDTO;
import com.phegondev.InventoryMgtSystem.dtos.CategoryDTO;
import com.phegondev.InventoryMgtSystem.dtos.TransactionDTO;
import com.phegondev.InventoryMgtSystem.dtos.RestaurantDTO;
import com.phegondev.InventoryMgtSystem.dtos.AttractionDTO;
import com.phegondev.InventoryMgtSystem.dtos.ReviewDTO;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setSkipNullEnabled(true)
                // Prefer merging to avoid replacing with potentially unmodifiable lists
                .setCollectionsMergeEnabled(true);

        // Convert any source Collection into a mutable List on destination
        Converter<Collection, List> toMutableList = new Converter<Collection, List>() {
            @Override
            public List convert(MappingContext<Collection, List> context) {
                Collection source = context.getSource();
                if (source == null) return null;
                return new ArrayList<>(source);
            }
        };
        mm.addConverter(toMutableList);

        // Avoid mapping nested collections/associations that cause converter errors or cycles
        mm.createTypeMap(User.class, UserDTO.class)
                .addMappings(mapper -> mapper.skip(UserDTO::setTransactions))
                .implicitMappings();

        mm.createTypeMap(Category.class, CategoryDTO.class)
                .addMappings(mapper -> mapper.skip(CategoryDTO::setProducts))
                .implicitMappings();

        // For Restaurant -> RestaurantDTO, skip reviews to avoid collection mapping issues
        mm.createTypeMap(Restaurant.class, RestaurantDTO.class)
                .addMappings(mapper -> mapper.skip(RestaurantDTO::setReviews))
                .implicitMappings();

        // For Attraction -> AttractionDTO, skip reviews to avoid collection mapping issues
        mm.createTypeMap(Attraction.class, AttractionDTO.class)
                .addMappings(mapper -> mapper.skip(AttractionDTO::setReviews))
                .implicitMappings();

        // For Review -> ReviewDTO, no associations to skip
        mm.createTypeMap(Review.class, ReviewDTO.class)
                .implicitMappings();

        // For Transaction -> TransactionDTO: no global skips here to avoid configuration conflicts.
        // We map associations explicitly in the service layer.
        return mm;
    }
}

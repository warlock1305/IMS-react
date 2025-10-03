package com.phegondev.InventoryMgtSystem.services;


import com.phegondev.InventoryMgtSystem.dtos.AttractionDTO;

import java.util.List;

public interface AttractionService {
    List<AttractionDTO> getAllAttractions();
    AttractionDTO getAttractionById(Long id);
    AttractionDTO createAttraction(AttractionDTO attractionDTO);
    AttractionDTO updateAttraction(Long id, AttractionDTO attractionDTO);
    void deleteAttraction(Long id);
}

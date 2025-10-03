package com.phegondev.InventoryMgtSystem.controllers;

import com.phegondev.InventoryMgtSystem.dtos.AttractionDTO;
import com.phegondev.InventoryMgtSystem.services.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping
    public List<AttractionDTO> getAllAttractions() {
        return attractionService.getAllAttractions();
    }

    @GetMapping("/{id}")
    public AttractionDTO getAttractionById(@PathVariable Long id) {
        return attractionService.getAttractionById(id);
    }

    @PostMapping
    public AttractionDTO createAttraction(@RequestBody AttractionDTO attractionDTO) {
        return attractionService.createAttraction(attractionDTO);
    }

    @PutMapping("/{id}")
    public AttractionDTO updateAttraction(@PathVariable Long id, @RequestBody AttractionDTO attractionDTO) {
        return attractionService.updateAttraction(id, attractionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAttraction(@PathVariable Long id) {
        attractionService.deleteAttraction(id);
    }
}

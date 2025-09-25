package com.phegondev.InventoryMgtSystem.services.impl;


import com.phegondev.InventoryMgtSystem.dtos.Response;
import com.phegondev.InventoryMgtSystem.dtos.SupplierDTO;
import com.phegondev.InventoryMgtSystem.exceptions.NotFoundException;
import com.phegondev.InventoryMgtSystem.models.Supplier;
import com.phegondev.InventoryMgtSystem.repositories.SupplierRepository;
import com.phegondev.InventoryMgtSystem.services.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
 
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {


    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;


    @Override
    public Response addSupplier(SupplierDTO supplierDTO) {

        Supplier supplierToSave = modelMapper.map(supplierDTO, Supplier.class);

        supplierRepository.save(supplierToSave);

        return Response.builder()
                .status(200)
                .message("Supplier Saved Successfully")
                .build();
    }

    @Override
    public Response updateSupplier(Long id, SupplierDTO supplierDTO) {

        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));

        if (supplierDTO.getName() != null) existingSupplier.setName(supplierDTO.getName());
        if (supplierDTO.getContactInfo() != null) existingSupplier.setContactInfo(supplierDTO.getContactInfo());
        if (supplierDTO.getAddress() != null) existingSupplier.setAddress(supplierDTO.getAddress());

        supplierRepository.save(existingSupplier);

        return Response.builder()
                .status(200)
                .message("Supplier Was Successfully Updated")
                .build();
    }

    @Override
    public Response getAllSupplier() {

        List<Supplier> suppliers = supplierRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<SupplierDTO> supplierDTOList = suppliers.stream()
                .map(supplier -> modelMapper.map(supplier, SupplierDTO.class))
                .toList();

        return Response.builder()
                .status(200)
                .message("success")
                .suppliers(supplierDTOList)
                .build();
    }

    @Override
    public Response getSupplierById(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));

        SupplierDTO supplierDTO = modelMapper.map(supplier, SupplierDTO.class);

        return Response.builder()
                .status(200)
                .message("success")
                .supplier(supplierDTO)
                .build();
    }

    @Override
    public Response deleteSupplier(Long id) {

        supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));

        supplierRepository.deleteById(id);

        return Response.builder()
                .status(200)
                .message("Supplier Was Successfully Deleted")
                .build();
    }
}

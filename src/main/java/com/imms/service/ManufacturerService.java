package com.imms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imms.model.Manufacturer;
import com.imms.repository.ManufacturerRepository;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public Manufacturer addManufacturer(Manufacturer manufacturer) {
        manufacturer.setStatus("Active");
        return manufacturerRepository.save(manufacturer);
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Optional<Manufacturer> getManufacturerById(String id) {
        return manufacturerRepository.findById(id);
    }

    public Manufacturer updateManufacturer(String id, Manufacturer manufacturerDetails) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found with id " + id));
        manufacturer.setName(manufacturerDetails.getName());
        manufacturer.setAddress(manufacturerDetails.getAddress());
        return manufacturerRepository.save(manufacturer);
    }

    public void deactivateManufacturer(String id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found with id " + id));
        manufacturer.setStatus("Inactive");
        manufacturerRepository.save(manufacturer);
    }
}
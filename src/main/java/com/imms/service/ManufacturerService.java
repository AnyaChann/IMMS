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
        return manufacturerRepository.save(manufacturer);
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Optional<Manufacturer> getManufacturerById(String id) {
        return manufacturerRepository.findById(Long.parseLong(id));
    }

    public Manufacturer updateManufacturer(String id, Manufacturer manufacturerDetails) {
        Manufacturer manufacturer = manufacturerRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException("Manufacturer not found with id " + id));
        manufacturer.setName(manufacturerDetails.getName());
        manufacturer.setAddress(manufacturerDetails.getAddress());
        return manufacturerRepository.save(manufacturer);
    }

    public void deleteManufacturer(String id) {
        manufacturerRepository.deleteById(Long.parseLong(id));
    }
}
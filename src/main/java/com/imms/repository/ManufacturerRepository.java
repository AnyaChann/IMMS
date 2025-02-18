package com.imms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.imms.model.Manufacturer;

@Repository
public interface ManufacturerRepository extends MongoRepository<Manufacturer, String> {
    List<Manufacturer> findByStatus(String status);
}
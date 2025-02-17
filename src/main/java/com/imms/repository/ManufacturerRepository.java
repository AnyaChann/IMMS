package com.imms.repository;

import com.imms.model.Manufacturer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends MongoRepository<Manufacturer, Long> {
}
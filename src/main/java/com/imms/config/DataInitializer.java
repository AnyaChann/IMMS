package com.imms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.imms.model.Manufacturer;
import com.imms.model.Product;
import com.imms.model.User;
import com.imms.repository.ManufacturerRepository;
import com.imms.repository.ProductRepository;
import com.imms.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initData(UserRepository userRepository, ManufacturerRepository manufacturerRepository, ProductRepository productRepository) {
        return args -> {
            // Add example users
            userRepository.save(new User(null, "admin", passwordEncoder.encode("admin123"), "ADMIN"));
            userRepository.save(new User(null, "user", passwordEncoder.encode("user123"), "USER"));

            // Add example manufacturers
            Manufacturer manufacturer1 = new Manufacturer();
            manufacturer1.setName("Manufacturer 1");
            manufacturer1.setAddress("1234 Street, City, Country");
            manufacturerRepository.save(manufacturer1);

            Manufacturer manufacturer2 = new Manufacturer();
            manufacturer2.setName("Manufacturer 2");
            manufacturer2.setAddress("5678 Avenue, City, Country");
            manufacturerRepository.save(manufacturer2);

            // Add example products
            Product product1 = new Product();
            product1.setName("Product 1");
            product1.setDescription("Description for Product 1");
            product1.setPrice(100.0);
            product1.setManufacturerId(manufacturer1);
            productRepository.save(product1);

            Product product2 = new Product();
            product2.setName("Product 2");
            product2.setDescription("Description for Product 2");
            product2.setPrice(200.0);
            product2.setManufacturerId(manufacturer2);
            productRepository.save(product2);
        };
    }
}
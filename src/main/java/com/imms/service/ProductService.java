package com.imms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imms.model.Product;
import com.imms.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        Long productId = Long.parseLong(id);
        return productRepository.findById(productId);
    }

    public Product updateProduct(String id, Product productDetails) {
        Long productId = Long.parseLong(id);
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setManufacturer(productDetails.getManufacturer());
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        Long productId = Long.parseLong(id);
        productRepository.deleteById(productId);
    }
}
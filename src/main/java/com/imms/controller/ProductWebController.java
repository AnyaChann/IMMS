package com.imms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imms.model.Product;
import com.imms.service.ManufacturerService;
import com.imms.service.ProductService;

@Controller
public class ProductWebController {

    private static final Logger logger = LoggerFactory.getLogger(ProductWebController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        logger.info("Fetching all products for web view");
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/new")
    public String createProductForm(Model model) {
        logger.info("Displaying form to create new product");
        model.addAttribute("product", new Product());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        return "product-form";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute Product product) {
        logger.info("Saving new product: {}", product.getName());
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String editProductForm(@PathVariable("id") String id, Model model) {
        logger.info("Displaying form to edit product with id: {}", id);
        Product product = productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        return "product-form";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable("id") String id, @ModelAttribute Product product) {
        logger.info("Updating product with id: {}", id);
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable("id") String id) {
        logger.info("Deleting product with id: {}", id);
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
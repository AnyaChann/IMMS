package com.imms.controller;

import java.util.List;

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

    @Autowired
    private ProductService productService;

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/new")
    public String createProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        return "product-form";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String editProductForm(@PathVariable("id") String id, Model model) {
        Product product = productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());
        return "product-form";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable("id") String id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
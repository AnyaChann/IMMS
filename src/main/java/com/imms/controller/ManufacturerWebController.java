package com.imms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.imms.model.Manufacturer;
import com.imms.service.ManufacturerService;

@Controller
public class ManufacturerWebController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/manufacturers")
    public String getAllManufacturers(Model model) {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        model.addAttribute("manufacturers", manufacturers);
        return "manufacturers";
    }

    @GetMapping("/manufacturers/new")
    public String createManufacturerForm(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturer-form";
    }

    @PostMapping("/manufacturers")
    public String saveManufacturer(@ModelAttribute Manufacturer manufacturer) {
        manufacturerService.addManufacturer(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/manufacturers/{id}")
    public String editManufacturerForm(@PathVariable("id") String id, Model model) {
        Manufacturer manufacturer = manufacturerService.getManufacturerById(id).orElseThrow(() -> new RuntimeException("Manufacturer not found"));
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer-form";
    }

    @PostMapping("/manufacturers/{id}")
    public String updateManufacturer(@PathVariable("id") String id, @ModelAttribute Manufacturer manufacturer) {
        manufacturerService.updateManufacturer(id, manufacturer);
        return "redirect:/manufacturers";
    }

    @PostMapping("/manufacturers/{id}/delete")
    public String deleteManufacturer(@PathVariable("id") String id) {
        manufacturerService.deleteManufacturer(id);
        return "redirect:/manufacturers";
    }
}
package com.yunus.haznedar.bootsboutique.controllers;

import java.lang.Iterable;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import com.yunus.haznedar.bootsboutique.services.BootService;
import com.yunus.haznedar.bootsboutique.entities.Boot;
import com.yunus.haznedar.bootsboutique.enums.BootType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/boots")
public class BootController {
    private final BootService bootService;

    public BootController(final BootService bootService) {
        this.bootService = bootService;
    }

    @GetMapping("/")
    public Iterable<Boot> getAllBoots() {
        return bootService.getAllBoots();
    }

    @GetMapping("/{id}")
    public Optional<Boot> getBoot(@PathVariable Integer id) {
        return bootService.findBoot(id);
    }

    @GetMapping("/types")
    public List<BootType> getBootTypes() {
        return Arrays.asList(BootType.values());
    }

    @PostMapping("/")
    public Boot addBoot(@RequestBody Boot boot) {
        return bootService.addBoot(boot);
    }

    @DeleteMapping("/{id}")
    public Boot deleteBoot(@PathVariable("id") Integer id) {

        return bootService.deleteBoot(id);
    }

    @PutMapping("/{id}/quantity/increment")
    public Boot incrementQuantity(@PathVariable("id") Integer id) {
        return bootService.incrementQuantity(id);
    }

    @PutMapping("/{id}/quantity/decrement")
    public Boot decrementQuantity(@PathVariable("id") Integer id) {
        return bootService.decrementQuantity(id);

    }

    @GetMapping("/search")
    public List<Boot> searchBoots(@RequestParam(required = false) String material,
                                  @RequestParam(required = false) BootType type, @RequestParam(required = false) Float size,
                                  @RequestParam(required = false, name = "quantity") Integer minQuantity) {
        return bootService.searchBoots(material, type, size, minQuantity);
    }
}

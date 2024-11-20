package com.prashantjain.yummyrest.controller;

import com.prashantjain.yummyrest.dto.CustomerRequest;
import com.prashantjain.yummyrest.dto.LoginRequest;
import com.prashantjain.yummyrest.dto.ProductRequest;
import com.prashantjain.yummyrest.entity.Product;
import com.prashantjain.yummyrest.service.CustomerService;
import com.prashantjain.yummyrest.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }


    @PutMapping ("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest request) {
    return ResponseEntity.ok(productService.updateProduct(id,request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> product = productService.getProducts();
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
            return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProducts() {
        return ResponseEntity.ok(productService.deleteProducts());

    }

    @GetMapping("/top2")
    public ResponseEntity<List<Product>> product15To30() {
        return ResponseEntity.ok(productService.product15To30());

    }
}

package com.prashantjain.yummyrest.service;

import com.prashantjain.yummyrest.dto.ProductRequest;
import com.prashantjain.yummyrest.entity.Product;
import com.prashantjain.yummyrest.mapper.ProductMapper;
import com.prashantjain.yummyrest.repo.ProductRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repo;
    private final ProductMapper mapper;
    public String createProduct(ProductRequest request) {
        Product product = mapper.toEntity(request);
        repo.save(product);
        return "Created";
    }


    public String updateProduct(Long id, @Valid ProductRequest request) {
        Product product = mapper.toEntity(request);
        Product existingProduct = repo.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            repo.save(existingProduct);
            return "Updated";
        }
       else{
           return "Product not found";
        }
    }

    public Product getProduct(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Product> getProducts() {
        return repo.findAll();
    }

    public String deleteProduct(Long id) {
        if(repo.findById(id).isPresent()) {
            repo.deleteById(id);
            return "Deleted";
        }else{
            return "Product not found";
        }
    }

    public String deleteProducts() {
        if(!repo.findAll().isEmpty()) {
            repo.deleteAll();
            return "Deleted";
        }else{
            return "No products found";
        }
    }

    public List<Product> product15To30() {
        return repo.products15To30();
    }
}

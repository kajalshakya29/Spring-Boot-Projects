package com.customer.rest.controller;

import com.customer.rest.entities.Product;
import com.customer.rest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 1. Get all products for a specific customer
    @GetMapping("/customers/{customerId}/products")
    public List<Product> retrieveProductForCustomer(@PathVariable String customerId) {
        return customerService.retrieveProducts(customerId);
    }

    // 2. Get details of a specific product for a specific customer
    @GetMapping("/customers/{customerId}/products/{productId}")
    public Product retrieveDetailsForProduct(@PathVariable String customerId, @PathVariable String productId) {
        return customerService.retrieveProduct(customerId, productId);
    }

    // 3. Register/Add a new product for a customer
    @PostMapping("/customers/{customerId}/products")
    public ResponseEntity<Void> registerCustomerForProduct(
            @PathVariable String customerId, @RequestBody Product newProduct) {

        Product product = customerService.addProduct(customerId, newProduct);

        if (product == null) {
            return ResponseEntity.noContent().build();
        }

        // Creating the URI for the newly created resource
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
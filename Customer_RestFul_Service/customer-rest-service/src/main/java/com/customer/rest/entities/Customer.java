package com.customer.rest.entities;

import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String description;
    private List<Product> products;

    public Customer(String id, String name, String description, List<Product> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public List<Product> getProducts() { return products; }
}
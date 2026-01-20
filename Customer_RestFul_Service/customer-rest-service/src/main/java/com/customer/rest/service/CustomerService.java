package com.customer.rest.service;

import com.customer.rest.entities.Customer;
import com.customer.rest.entities.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomerService {

    private static List<Customer> customers = new ArrayList<>();

    static {
        // Initialize Data as per your screenshots
        Product product1 = new Product("Product_A", "Slip-Ons", "Rubber water proof sole");
        Product product2 = new Product("Product_B", "Sneakers", "Armed with a rubber sole");
        Product product3 = new Product("Product_C", "Loafers", "Neutral tan base");
        Product product4 = new Product("Product_D", "Brogues", "A classic combination");
        Product product5 = new Product("Product_E", "Juttis", "Prettiest juttis with hand embroidery");

        Customer payal = new Customer("Customer1", "Payal Sharma", "Hiker and Architect",
                new ArrayList<>(Arrays.asList(product1, product2, product3, product4)));

        Customer neha = new Customer("Customer2", "Neha Singhania", "Programmer and Designer",
                new ArrayList<>(Arrays.asList(product1, product2, product3, product4)));

        customers.add(payal);
        customers.add(neha);
    }

    public List<Customer> retrieveAllCustomers() {
        return customers;
    }

    public Customer retrieveCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public List<Product> retrieveProducts(String customerId) {
        Customer customer = retrieveCustomer(customerId);
        if (customer == null) {
            return null;
        }
        return customer.getProducts();
    }

    public Product retrieveProduct(String customerId, String productId) {
        Customer customer = retrieveCustomer(customerId);
        if (customer == null) {
            return null;
        }
        for (Product product : customer.getProducts()) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public Product addProduct(String customerId, Product product) {
        Customer customer = retrieveCustomer(customerId);
        if (customer == null) {
            return null;
        }
        customer.getProducts().add(product);
        return product;
    }
}
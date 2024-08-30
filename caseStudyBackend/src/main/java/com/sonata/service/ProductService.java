package com.sonata.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonata.model.Product;
import com.sonata.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchProducts(String query, int page, int size) {
        return productRepository.findByNameContainingIgnoreCase(query);
    }

    public List<Product> filterProducts(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice,
                                        String brand, Integer ratings, int page, int size) {
        return productRepository.filterProducts(categoryId, minPrice, maxPrice, brand, ratings);
    }

    public List<Product> sortProducts(String sortBy, String order, int page, int size) {
        switch (sortBy) {
            case "price":
                return order.equals("asc") ? productRepository.findAllByOrderByPriceAsc() :
                        productRepository.findAllByOrderByPriceDesc();
            case "popularity":
                return productRepository.findAllByOrderByPopularityDesc();
            case "newest":
                return productRepository.findAllByOrderByCreatedAtDesc();
            default:
                throw new IllegalArgumentException("Invalid sort criteria");
        }
    }

    public List<Product> advancedSearch(String query, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice,
                                        String brand, Integer ratings, int page, int size) {
        return productRepository.advancedSearch(query, categoryId, minPrice, maxPrice, brand, ratings);
    }
}

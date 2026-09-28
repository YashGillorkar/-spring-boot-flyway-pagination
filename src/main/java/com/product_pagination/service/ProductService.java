package com.product_pagination.service;


import com.product_pagination.dto.PagedResponse;
import com.product_pagination.entity.Product;

public interface ProductService {

    PagedResponse<Product> getAllProducts(int page, int size, String sortBy, String sortDir);

    PagedResponse<Product> getByCategory(String category, int page, int size, String sortBy, String sortDir);

    PagedResponse<Product> getByPriceRange(String minPrice, String maxPrice, int page, int size, String sortBy, String sortDir);

    PagedResponse<Product> getByInStock(int page, int size, String sortBy, String sortDir);

    PagedResponse<Product> searchByName(String keyword, int page, int size, String sortBy, String sortDir);
}

package com.product_pagination.service.impl;

import com.product_pagination.constant.AppConstant;
import com.product_pagination.dto.PagedResponse;
import com.product_pagination.entity.Product;
import com.product_pagination.repository.ProductRepository;
import com.product_pagination.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    @Override
    public PagedResponse<Product> getAllProducts(int page, int size, String sortBy, String sortDir) {
        Pageable pageable = buildPageable(page, size,sortBy,sortDir);
        Page<Product> products =  productRepository.findAll(pageable);
        return buildResponse(products);
    }

    @Override
    public PagedResponse<Product> getByCategory(String category, int page, int size, String sortBy, String sortDir) {
        Pageable pageable = buildPageable(page, size, sortBy, sortDir);
        Page<Product> products = productRepository.findByCategory(category,pageable);
        return buildResponse(products);
    }

    @Override
    public PagedResponse<Product> getByPriceRange(String minPrice, String maxPrice, int page, int size, String sortBy, String sortDir) {
        Pageable pageable = buildPageable(page,size,sortBy,sortDir);
        Page<Product> products = productRepository.findByPriceBetween(Double.valueOf(minPrice),Double.valueOf(maxPrice),pageable);
        return buildResponse(products);
    }

    @Override
    public PagedResponse<Product> getByInStock(int page, int size, String sortBy, String sortDir) {
        Pageable pageable = buildPageable(page,size,sortBy,sortDir);
        Page<Product> products = productRepository.findByInStockTrue(pageable);
        return buildResponse(products);
    }

    @Override
    public PagedResponse<Product> searchByName(String keyword, int page, int size, String sortBy, String sortDir) {
        Pageable pageable = buildPageable(page,size,sortBy,sortDir);
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(keyword,pageable);
        return buildResponse(products);
    }

    // Builds Pageable object
    private Pageable buildPageable(int page, int size, String sortBy, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(AppConstant.DESCENDING)
                ? Sort.by(sortBy).descending()
                :Sort.by(sortBy).ascending();
        return PageRequest.of(page,size,sort);
    }

    // Converts Spring's Page<T> → your PagedResponse<T>
    private <T>PagedResponse<T> buildResponse(Page<T> page){
        return new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getNumberOfElements(),
                page.getTotalPages(),
                page.isLast());
    }
}

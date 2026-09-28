package com.product_pagination.controller;

import com.product_pagination.constant.AppConstant;
import com.product_pagination.dto.PagedResponse;
import com.product_pagination.entity.Product;
import com.product_pagination.service.ProductService;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    //Constructor-based Dependency Injection
    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping
    private ResponseEntity<PagedResponse<Product>> getAllProducts(
            @RequestParam (defaultValue = AppConstant.DEFAULT_PAGE)int page,
            @RequestParam (defaultValue = AppConstant.DEFAULT_PAGE_SIZE)int size,
            @RequestParam (defaultValue = AppConstant.DEFAULT_SORT_By)String sortBy,
            @RequestParam (defaultValue = AppConstant.ASCENDING)String sortDir
    ){

        PagedResponse<Product> response = productService.getAllProducts(page, size,sortBy,sortDir);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category")
    private ResponseEntity<PagedResponse<Product>> getByCategory(
            @RequestParam String category,
            @RequestParam (defaultValue = AppConstant.DEFAULT_PAGE) int page,
            @RequestParam (defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam (defaultValue = AppConstant.DEFAULT_SORT_By) String sortBy,
            @RequestParam (defaultValue = AppConstant.ASCENDING) String sortDir
    ){
        PagedResponse<Product> response = productService.getByCategory(category,page,size,sortBy,sortDir);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/price-range")
    private ResponseEntity<PagedResponse<Product>> getByPriceRange(
            @RequestParam String minPrice,
            @RequestParam String maxPrice,
            @RequestParam (defaultValue = AppConstant.DEFAULT_PAGE) int page,
            @RequestParam (defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam (defaultValue = AppConstant.DEFAULT_SORT_By) String sortBy,
            @RequestParam (defaultValue = AppConstant.ASCENDING) String sortDir){

        PagedResponse<Product> response = productService.getByPriceRange(minPrice,maxPrice,page,size, sortBy,sortDir);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/in-stock")
    private ResponseEntity<PagedResponse<Product>> getByInStock(
            @RequestParam (defaultValue = AppConstant.DEFAULT_PAGE) int page,
            @RequestParam (defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam (defaultValue = AppConstant.DEFAULT_SORT_By) String sortBy,
            @RequestParam (defaultValue = AppConstant.ASCENDING) String sortDir){

        PagedResponse<Product> response = productService.getByInStock(page,size, sortBy,sortDir);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    private ResponseEntity<PagedResponse<Product>> searchByName(
            @RequestParam String keyword,
            @RequestParam (defaultValue = AppConstant.DEFAULT_PAGE) int page,
            @RequestParam (defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam (defaultValue = AppConstant.DEFAULT_SORT_By) String sortBy,
            @RequestParam (defaultValue = AppConstant.ASCENDING) String sortDir
    ){
        PagedResponse<Product> response = productService.searchByName(keyword,page,size,sortBy,sortDir);
        return ResponseEntity.ok(response);
    }



}

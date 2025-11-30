package com.example.shawn.controller;

import com.example.shawn.dto.ApiResponse;
import com.example.shawn.entity.Product;
import com.example.shawn.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ApiResponse<List<Product>> list() {
        return ApiResponse.ok(productService.listProducts());
    }

    @PostMapping
    public ApiResponse<Product> create(@RequestBody @Valid ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        return ApiResponse.ok("创建成功", productService.create(product, request.getCategoryId()));
    }

    @PutMapping("/{id}/stock")
    public ApiResponse<Product> updateStock(@PathVariable Long id, @RequestParam Integer stock) {
        return ApiResponse.ok("库存已更新", productService.updateStock(id, stock));
    }

    @Data
    public static class ProductRequest {
        @NotNull
        private String name;
        private String description;
        @NotNull
        private java.math.BigDecimal price;
        @Min(0)
        private Integer stock;
        @NotNull
        private Long categoryId;
    }
}

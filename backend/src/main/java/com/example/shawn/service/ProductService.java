package com.example.shawn.service;

import com.example.shawn.entity.Category;
import com.example.shawn.entity.Product;
import com.example.shawn.exception.BusinessException;
import com.example.shawn.repository.CategoryRepository;
import com.example.shawn.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public Product create(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException("分类不存在"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product updateStock(Long productId, Integer stock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException("商品不存在"));
        product.setStock(stock);
        return productRepository.save(product);
    }
}

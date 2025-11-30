package com.example.shawn.service;

import com.example.shawn.entity.Product;
import com.example.shawn.entity.ShoppingCartItem;
import com.example.shawn.entity.User;
import com.example.shawn.exception.BusinessException;
import com.example.shawn.repository.ProductRepository;
import com.example.shawn.repository.ShoppingCartItemRepository;
import com.example.shawn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ShoppingCartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<ShoppingCartItem> listItems(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("用户不存在"));
        return cartItemRepository.findByUser(user);
    }

    public ShoppingCartItem addItem(String username, Long productId, Integer quantity) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("用户不存在"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException("商品不存在"));
        ShoppingCartItem item = new ShoppingCartItem();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }
}

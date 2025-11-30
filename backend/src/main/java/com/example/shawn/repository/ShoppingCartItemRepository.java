package com.example.shawn.repository;

import com.example.shawn.entity.ShoppingCartItem;
import com.example.shawn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    List<ShoppingCartItem> findByUser(User user);
}

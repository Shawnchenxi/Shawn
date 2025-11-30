package com.example.shawn.controller;

import com.example.shawn.dto.ApiResponse;
import com.example.shawn.entity.ShoppingCartItem;
import com.example.shawn.service.CartService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ApiResponse<List<ShoppingCartItem>> list(@RequestParam String username) {
        return ApiResponse.ok(cartService.listItems(username));
    }

    @PostMapping
    public ApiResponse<ShoppingCartItem> add(@RequestBody AddCartRequest request) {
        return ApiResponse.ok("已加入购物车", cartService.addItem(request.getUsername(), request.getProductId(), request.getQuantity()));
    }

    @Data
    public static class AddCartRequest {
        @NotBlank
        private String username;
        @Min(1)
        private Integer quantity;
        private Long productId;
    }
}

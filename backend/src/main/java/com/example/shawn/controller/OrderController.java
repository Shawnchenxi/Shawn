package com.example.shawn.controller;

import com.example.shawn.dto.ApiResponse;
import com.example.shawn.entity.CustomerOrder;
import com.example.shawn.service.OrderService;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ApiResponse<List<CustomerOrder>> list(@RequestParam String username) {
        return ApiResponse.ok(orderService.listOrders(username));
    }

    @PostMapping
    public ApiResponse<CustomerOrder> create(@RequestBody CreateOrderRequest request) {
        return ApiResponse.ok("订单已创建", orderService.createOrder(request.getUsername(), request.getTotalAmount()));
    }

    @Data
    public static class CreateOrderRequest {
        @NotBlank
        private String username;
        private BigDecimal totalAmount;
    }
}

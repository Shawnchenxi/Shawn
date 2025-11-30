package com.example.shawn.service;

import com.example.shawn.entity.CustomerOrder;
import com.example.shawn.entity.User;
import com.example.shawn.exception.BusinessException;
import com.example.shawn.repository.CustomerOrderRepository;
import com.example.shawn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerOrderRepository orderRepository;
    private final UserRepository userRepository;

    public List<CustomerOrder> listOrders(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("用户不存在"));
        return orderRepository.findByUser(user);
    }

    public CustomerOrder createOrder(String username, BigDecimal totalAmount) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("用户不存在"));
        CustomerOrder order = new CustomerOrder();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus("CREATED");
        return orderRepository.save(order);
    }
}

package com.example.shawn.repository;

import com.example.shawn.entity.CustomerOrder;
import com.example.shawn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByUser(User user);
}

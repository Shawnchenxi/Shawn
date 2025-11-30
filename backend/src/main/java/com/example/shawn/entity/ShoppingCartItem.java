package com.example.shawn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
public class ShoppingCartItem extends BaseEntity {
    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private Integer quantity;
}

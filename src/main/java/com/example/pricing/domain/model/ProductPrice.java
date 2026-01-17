package com.example.pricing.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductPrice {
    Long id;
    float price;
    int stockQuantity;

    public ProductPrice(Long id, float price, int stockQuantity) {
        this.id = id;
        this.price = price;
        this.stockQuantity = stockQuantity;
    };

    public ProductPrice(Long id, float price) {
        this.id = id;
        this.price = price;
    };

}

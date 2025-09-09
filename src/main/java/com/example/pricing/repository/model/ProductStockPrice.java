package com.example.pricing.repository.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class ProductStockPrice {
    float price;
    int stockQuantity;

    public ProductStockPrice() {

    }
}

package com.example.pricing.repository.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPriceEntity {
    Long id;
    float price;

    public ProductPriceEntity(Long id, float price) {
        this.id = id;
        this.price = price;
    }
}

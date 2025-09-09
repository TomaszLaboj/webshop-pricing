package com.example.pricing.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.example.pricing.repository.model.Discount;
import com.example.pricing.repository.model.ProductPriceEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public static ProductPrice fromEntity(ProductPriceEntity productPriceEntity) {
        return new ProductPrice(
                productPriceEntity.getId(),
                productPriceEntity.getStockPrices().getFirst().getPrice()
        );
    }
}

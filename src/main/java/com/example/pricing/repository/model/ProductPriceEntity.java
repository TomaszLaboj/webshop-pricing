package com.example.pricing.repository.model;

import java.util.ArrayList;
import java.util.List;

import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.domain.model.ProductStockPrice;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductPriceEntity {

    @Id
    Long id;
    List<Discount> discounts;
    @ElementCollection
    List<ProductStockPrice> stockPrices;
    public ProductPriceEntity(Long id, List<Discount> discounts) {
        this.id = id;
        this.discounts = discounts;
        this.stockPrices = new ArrayList<>();
    }
    public ProductPriceEntity(Long id) {
        this.id = id;
        this.discounts = new ArrayList<>();
        this.stockPrices = new ArrayList<>();
    }

    public ProductStockPrice addStockPrice(ProductStockPrice productStockPrice) {
        this.stockPrices.add(productStockPrice);
        return productStockPrice;
    }

    public Discount addDiscount(Discount discount) {
        this.discounts.add(discount);
        return discount;
    }

    public ProductPrice toProductPrice() {
        return new ProductPrice(this.id, stockPrices.getFirst().getPrice(), stockPrices.getFirst().getStockQuantity());
    }

    public ProductPriceEntity() {}
}

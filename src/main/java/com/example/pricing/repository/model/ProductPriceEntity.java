package com.example.pricing.repository.model;

import java.util.ArrayList;
import java.util.List;

import com.example.pricing.domain.model.ProductPrice;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductPriceEntity {

    @Id
    Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    List<ProductStockPrice> stockPrices;

    public ProductPriceEntity(Long id) {
        this.id = id;
        this.stockPrices = new ArrayList<>();
    }

    public ProductPrice toProductPrice() {
        return new ProductPrice(this.id, stockPrices.getFirst().getPrice(), stockPrices.getFirst().getStockQuantity());
    }

    public ProductPriceEntity() {}
}

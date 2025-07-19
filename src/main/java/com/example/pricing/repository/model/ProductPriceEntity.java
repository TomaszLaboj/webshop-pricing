package com.example.pricing.repository.model;

import java.util.ArrayList;
import java.util.List;

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
    float price;
    List<Discount> discounts;

    public ProductPriceEntity(Long id, float price, List<Discount> discounts) {
        this.id = id;
        this.price = price;
        this.discounts = discounts;
    }

    public ProductPriceEntity() {}
}

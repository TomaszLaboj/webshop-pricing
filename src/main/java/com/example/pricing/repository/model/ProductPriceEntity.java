package com.example.pricing.repository.model;

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

    public ProductPriceEntity(Long id, float price) {
        this.id = id;
        this.price = price;
    }

    public ProductPriceEntity() {}
}

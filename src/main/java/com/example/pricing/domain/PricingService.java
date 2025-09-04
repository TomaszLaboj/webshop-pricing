package com.example.pricing.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pricing.domain.model.Price;
import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.domain.model.ProductPriceQuantity;
import com.example.pricing.domain.model.ProductQuantity;
import com.example.pricing.domain.model.Saving;
import com.example.pricing.kafka.KafkaProducer;
import com.example.pricing.repository.PricingRepositoryPostgres;
import com.example.pricing.repository.model.Discount;
import com.example.pricing.repository.model.ProductPriceEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class PricingService {

    private final PricingRepositoryPostgres pricingRepositoryPostgres;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public PricingService(PricingRepositoryPostgres pricingRepositoryPostgres,  KafkaProducer kafkaProducer) {
        this.pricingRepositoryPostgres = pricingRepositoryPostgres;
        this.kafkaProducer = kafkaProducer;
    };

    public ProductPrice createPrice(ProductPrice productPrice) {
        pricingRepositoryPostgres.createPrice(new ProductPriceEntity(productPrice.getId(), productPrice.getPrice(), productPrice.getDiscounts()));
        return productPrice;
    };

    public List<ProductPrice> updatePrices(List<ProductPrice> updatedProducts) throws JsonProcessingException {
        List<ProductPrice> updated = updatedProducts.stream().map(product -> ProductPrice.fromEntity(pricingRepositoryPostgres.updatePrice(product))).toList();
        kafkaProducer.sendUpdated(updated);
        return updatedProducts;
    };

    public List<Price> calculateDiscounts(List<ProductQuantity> listOfProducts) throws JsonProcessingException {
        List<Price> productPrices = new ArrayList<>();

        for(ProductQuantity productQuantity : listOfProducts) {
            ProductPrice productPrice = ProductPrice.fromEntity(pricingRepositoryPostgres.findById(productQuantity.getProductId()));

            if(productPrice.getDiscounts() != null) {
                if(productQuantity.getQuantity() >= 3 && productPrice.getDiscounts().contains(Discount.THREE_FOR_TWO)) {
                    int numberOfSavings = Math.floorDiv(Long.valueOf(productQuantity.getQuantity()).intValue(), 3);
                    productPrices.add(new ProductPriceQuantity(productPrice.getId(), productPrice.getPrice(), productPrice.getDiscounts(), productQuantity.getQuantity() -  numberOfSavings));
                    productPrices.add(new Saving(Discount.THREE_FOR_TWO.toString(), productPrice.getPrice() * numberOfSavings * -1));
                }
            } else if (productQuantity.getQuantity() >= 2 && productPrice.getDiscounts().contains(Discount.GET_ONE_GET_SECOND_HALF_PRICE)) {
                int numberOfSavings = Math.floorDiv(Long.valueOf(productQuantity.getQuantity()).intValue(), 2);
                productPrices.add(new ProductPriceQuantity(productPrice.getId(), productPrice.getPrice(), productPrice.getDiscounts(), productQuantity.getQuantity() -  numberOfSavings));
                productPrices.add(new Saving(Discount.THREE_FOR_TWO.toString(), productPrice.getPrice() * numberOfSavings * -0.5f));
            }
            if (productPrice.getDiscounts().contains(Discount.WEDNESDAY_DISCOUNT) && DayOfWeek.from(LocalDate.now()).toString() == "WEDNESDAY") {
                productPrices.add(new ProductPriceQuantity(productPrice.getId(), productPrice.getPrice() * 0.9f, productPrice.getDiscounts(), productQuantity.getQuantity()));
            }
            productPrices.add(new ProductPriceQuantity(productPrice.getId(), productPrice.getPrice(), productPrice.getDiscounts(), productQuantity.getQuantity()));
        }
        kafkaProducer.sendCalculatePrice(productPrices);
        return productPrices;
    }

    public void checkPrices(List<Integer> listOfProducts) {

    }
}

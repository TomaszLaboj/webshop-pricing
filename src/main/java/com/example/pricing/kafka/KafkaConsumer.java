package com.example.pricing.kafka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.pricing.domain.PricingService;
import com.example.pricing.domain.model.ProductPrice;
import com.example.pricing.domain.model.ProductQuantity;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class KafkaConsumer {

    PricingService pricingService;

    @Autowired
    public KafkaConsumer(PricingService pricingService){
        this.pricingService = pricingService;
    }

    @KafkaListener(id = "price", groupId = "pricing", topics = {"create-product"})
    public void listenCreatePrice(ProductPrice productPrice) {
        pricingService.createPrice(productPrice);
    }

    @KafkaListener(id = "prices", groupId = "pricing", topics = {"update-price"})
    public void listenUpdatePrices(List<ProductPrice> updatedProducts) throws JsonProcessingException {
        pricingService.updatePrices(updatedProducts);
    }

    @KafkaListener(id = "check-price", groupId = "pricing", topics = {"calculate-price"})
    public void listenCalculatePrice(List<ProductQuantity> listOfProducts) throws JsonProcessingException {
        pricingService.calculatePrices(listOfProducts);
    }
}

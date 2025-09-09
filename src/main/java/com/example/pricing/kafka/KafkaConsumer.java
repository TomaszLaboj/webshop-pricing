package com.example.pricing.kafka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.pricing.domain.PricingService;
import com.example.pricing.domain.model.ProductPrice;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class KafkaConsumer {

    PricingService pricingService;

    @Autowired
    public KafkaConsumer(PricingService pricingService){
        this.pricingService = pricingService;
    }


    @KafkaListener(id = "update-stock", groupId = "pricing", topics = { "update-stock"})
    public void listenUpdateStock(ProductPrice product) throws JsonProcessingException {
        pricingService.updateStock(product);
        System.out.println("I have message");
    }

    @KafkaListener(id = "check-price", groupId = "pricing", topics = { "check-price"})
    public void listenCheckPrice(Long id) throws JsonProcessingException {
        pricingService. checkPrice(id);
    }
}

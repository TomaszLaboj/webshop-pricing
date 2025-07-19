package com.example.pricing.kafka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.pricing.domain.model.Price;
import com.example.pricing.domain.model.ProductPrice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    ObjectMapper mapper = new ObjectMapper();

    public void sendUpdated(List<ProductPrice> updatedProducts) throws JsonProcessingException {
        kafkaTemplate.send("updated", mapper.writeValueAsString(updatedProducts));
    }

    public void sendCalculatePrice(List<Price> productPrices) throws JsonProcessingException {
        kafkaTemplate.send("calculate-price", mapper.writeValueAsString(productPrices));
    }
}

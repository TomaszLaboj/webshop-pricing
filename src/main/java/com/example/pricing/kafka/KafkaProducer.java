package com.example.pricing.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.pricing.domain.model.ProductsCalculatedPrices;
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

    public void sendPrices(ProductsCalculatedPrices productPrices) throws JsonProcessingException {
        kafkaTemplate.send("updated", mapper.writeValueAsString(productPrices));
    }


}

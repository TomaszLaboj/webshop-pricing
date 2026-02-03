package com.example.pricing.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.pricing.LoggingController;
import com.example.pricing.domain.model.ProductPrice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaProducer {

    private final KafkaTemplate<Object, String> kafkaTemplate;
    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    public KafkaProducer(KafkaTemplate<Object, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    ObjectMapper mapper = new ObjectMapper();

    public void sendPrice(ProductPrice productPrice) throws JsonProcessingException {
        kafkaTemplate.send("send-price", mapper.writeValueAsString(productPrice));
        logger.info("Product price sent.");
    }

    public void sendMessage(String message) throws JsonProcessingException {
        logger.info("sending message: " + message);
        kafkaTemplate.send("send-message", mapper.writeValueAsString(message));
    }
}

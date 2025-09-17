package com.example.pricing.kafka;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.pricing.LoggingController;
import com.example.pricing.domain.PricingService;
import com.example.pricing.domain.model.ProductPrice;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class KafkaConsumer {
    PricingService pricingService;

    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    public KafkaConsumer(PricingService pricingService){
        this.pricingService = pricingService;
    }


    @KafkaListener(id = "update-stock", groupId = "pricing", topics = { "update-stock"})
    public void listenUpdateStock(ProductPrice product) throws JsonProcessingException {
        pricingService.updateStock(product);
        logger.info("received message: ");
    }

    @KafkaListener(id = "check-price", groupId = "pricing", topics = { "check-price"})
    public void listenCheckPrice(Long id) throws JsonProcessingException {
        pricingService. checkPrice(id);
    }
}

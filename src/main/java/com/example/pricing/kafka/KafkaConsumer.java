package com.example.pricing.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @KafkaListener(id = "update-stock", groupId = "pricing", topics = { "update-stock"}, containerFactory = "productPriceKafkaListenerContainerFactory")
    public void listenUpdateStock(ProductPrice product) throws JsonProcessingException {
        logger.info("Received message, id: " + product.getId() + ",stock: " + product.getStockQuantity() + ", price: " + product.getPrice());
    }

    @KafkaListener(id = "check-price", groupId = "pricing", topics = { "check-price"}, containerFactory = "checkPriceContainerConsumerFactory")
    public void listenCheckPrice(Long productId) throws JsonProcessingException {
    }
}

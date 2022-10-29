package com.example.card1.service.impl;

import com.example.card1.exception.WebException;
import com.example.card1.model.Address;
import com.example.card1.model.Card;
import com.example.card1.model.Product;
import com.example.card1.service.BuyServiceImpl;

import java.util.Arrays;

public class BuyService implements BuyServiceImpl {
    private StoreService storeService;
    private PaymentService paymentService;
    private DeliveryService deliveryService;

    public BuyService(StoreService storeService, PaymentService paymentService, DeliveryService deliveryService) {
        this.storeService = storeService;
        this.paymentService = paymentService;
        this.deliveryService = deliveryService;
    }

    @Override
    public void processBuy(String productId, Integer amount, Card card, Address address) throws WebException {
        Product product = storeService.searchProduct(productId,amount);

        if(product != null){
            if(paymentService.processBuy(card,product.getCost() * amount)){
                deliveryService.processDelivery(Arrays.asList(product),address);
                cardData(card);
            }

        }
    }

    @Override
    public String cardData (Card card){
        return "Tarjeta{" +
                "numero de tarjeta='" + card.getFrontNumbers() + '\'' +
                ", titular='" + card.getCardHolder() + '\'' +
                ", fecha de vencimiento='" + card.getExpirationDate() + '\'' +
                ", marca ='" + card.getCardName() + '\'' +
                '}';
    }


}

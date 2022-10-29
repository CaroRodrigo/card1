package com.example.card1.service;

import com.example.card1.exception.WebException;
import com.example.card1.model.Address;
import com.example.card1.model.Card;

public interface BuyServiceImpl {

    public void processBuy(String productId, Integer amount, Card card, Address address) throws WebException;

    public String cardData (Card card);

}

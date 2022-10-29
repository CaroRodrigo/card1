package com.example.card1.service.impl;

import com.example.card1.model.Address;
import com.example.card1.model.Product;

import java.util.List;

public class DeliveryService {


    public void processDelivery(List<Product> products, Address address){
        System.out.println("Enviando productos a " + address.getStreet() +" "+ address.getNumber() +","+ address.getNeighborhood());
    }

}

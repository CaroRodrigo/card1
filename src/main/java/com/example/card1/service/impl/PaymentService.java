package com.example.card1.service.impl;

import com.example.card1.enums.CardEnum;
import com.example.card1.exception.WebException;
import com.example.card1.model.Card;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentService {


    public Boolean processBuy(Card card, Integer amount) throws WebException {

        Boolean paymentDone = Boolean.FALSE;

        if (card.getFrontNumbers().isEmpty() || card.getFrontNumbers().isBlank() ||
                card.getExpirationDate().isEmpty() || card.getExpirationDate().isBlank() ||
                card.getCardHolder().isEmpty() || card.getCardHolder().isBlank() ||
                card.getCardName().isEmpty() || card.getCardName().isBlank()) {


            throw new WebException("Los datos de su tarjeta estan incompletos");

        }

        validateCardName(card.getCardName().toUpperCase());
        validateExpiration(card.getExpirationDate());

        if (amount > 1000) {
            throw new WebException("Operacion invalida: El monto debe ser menor a $1000");
        }

        System.out.println("Procesando el pago por " + amount);
        paymentDone = Boolean.TRUE;

        return paymentDone;


    }

    public void validateExpiration(String expirationDate) throws WebException {

        int count = 0;

        for(int i=0; i< expirationDate.length(); i++){
            count++;
        }

        if(count != 5){
            throw new WebException("Ingreso los datos de vencimiento correctamente: ( MM/YY )");
        }

        if (year(expirationDate) <= 22) {
            if (year(expirationDate) == 22 && month(expirationDate) <= 10 || year(expirationDate) < 22) {
                throw new WebException("Su tarjeta esta vencida, compruebe si ingreso los datos correctamente ( MM/YY )");
            }
        }

        System.out.println("Su tarjeta es valida para operar");

    }

    public void validateCardName(String cardName ) throws WebException {

        int count = 0;

        CardEnum[] cards = CardEnum.values();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].toString().equals(cardName)) {
                count ++;
            }
        }
        if (count < 1) {
            throw new WebException("Solo se admiten tarjetas: NARA, VISA o AMEX ");
        }

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("YY");

        Double day = Double.valueOf(localDate.format(formatterDay));
        Double month = Double.valueOf(localDate.format(formatterMonth));
        Double year = Double.valueOf(localDate.format(formatterYear));

        switch (cardName) {
            case "VISA":
                Double resultVisa = year/month;
                System.out.println("Tarjeta Visa, tasa por servicio: " + resultVisa +"%");
                break;
            case "AMEX":
                Double resultAmex = month*0.1;
                System.out.println("Tarjeta Amex, tasa por servicio: " + resultAmex +"%");
                break;
            case "NARA":
                Double resultNara = day*0.5;
                System.out.println("Tarjeta Nara, tasa por servicio : " + resultNara +"%");
                break;
        }
    }


    public Integer year(String expirationDate) {
        String string = expirationDate;
        Integer removeMonth = 2;

        int year = Integer.parseInt(string.substring(string.length() - removeMonth));

        return year;
    }

    public Integer month(String expirationDate) {
        String string = expirationDate;
        Integer save = 2;

        int month = Integer.parseInt(string.substring(0, save));

        return month;
    }



}

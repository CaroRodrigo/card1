package com.example.card1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    private String frontNumbers;
    private String cardHolder;
    private String expirationDate;
    private String cardName;

}

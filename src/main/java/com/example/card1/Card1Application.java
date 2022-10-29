package com.example.card1;

import com.example.card1.exception.WebException;
import com.example.card1.model.Address;
import com.example.card1.model.Card;
import com.example.card1.model.Product;
import com.example.card1.service.BuyServiceImpl;
import com.example.card1.service.impl.BuyService;
import com.example.card1.service.impl.DeliveryService;
import com.example.card1.service.impl.PaymentService;
import com.example.card1.service.impl.StoreService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Card1Application {

	public static void main(String[] args) throws WebException {
		Product productOne = new Product("1", 5, 300, "Mouse");
		Product productTwo = new Product("2", 5, 500, "Keyboard");
		Product productThree = new Product("3", 1, 1000, "Camera");


		Card card = new Card("1111222233334444", "Rodrigo Caro", "02/28","visa");


		Address address = new Address("Platon", "2848", "5008", "Los paraisos", "Cordoba");
		BuyServiceImpl buyService = new BuyService(new StoreService(Arrays.asList(productOne, productTwo,productThree)), new PaymentService(), new DeliveryService());
		System.out.println(buyService.cardData(card));


		buyService.processBuy("1",3,card,address);

	}

}

// Hola como andan?
//antes que nada disculpen la documentacion horrible que presento, estuve a 1000 esta semana.
// les comento caso feliz y caso triste...
// caso feliz linea 25
//caso triste : cualquier dato vacio, si la tarjeta esta vencida o el mes y año mal colocados, nombre de la tarjeta : solo acepta visa, amex y nara (bien escritos) para agregar mas tarjetas se deberia agregar a enum y switch

//Patron de diseño utilizado : Patrón template method

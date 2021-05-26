package com.assignment.creditcardsystem.service;

import com.assignment.creditcardsystem.entity.CreditCard;
import com.assignment.creditcardsystem.exception.CreditCardInvalidException;
import com.assignment.creditcardsystem.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<CreditCard> getAllCards() {
        //create a new list of CreditCard type
        List<CreditCard> cards = new ArrayList<CreditCard>();

        //find all credit cards from DB and store it in the list
        cardRepository.findAll().forEach(card -> cards.add(card));

        //return the list
        return cards;
    }

    public void saveCardDetails(CreditCard card) throws CreditCardInvalidException {
        //get the card number
        long cardNumber = card.getCardNumber();

        //check if its valid according to Luhn 10 algorithm
        boolean isValid = checkLuhnNumber(cardNumber);

        if(isValid) {
            //If valid, save the card details
            cardRepository.save(card);
        } else {
            //else, throw a custom CreditCardInvalidException exception
            throw new CreditCardInvalidException();
        }
    }

    /**
     * Returns true if given card number is valid based on Luhn 10 algorithm
     * */
    public boolean checkLuhnNumber(long cardNumber){
        //convert card number into a string
        String cardNo = String .valueOf (cardNumber);

        // get the card number length
        int cardNoLength = cardNo.length();

        int sum = 0;
        boolean isAltNumber = false;

        for (int i = cardNoLength - 1; i >= 0; i--) {
            int d = cardNo.charAt(i) - '0';

            // double the number
            if (isAltNumber == true)
                d = d * 2;

            // if doubling the digits ends in a 2 digit number,
            // we add each of the digit to end up with a single digit
            sum += d / 10;
            sum += d % 10;

            // this is to select alternate/ every second number for doubling
            isAltNumber = !isAltNumber;
        }
        return (sum % 10 == 0);
    }
}

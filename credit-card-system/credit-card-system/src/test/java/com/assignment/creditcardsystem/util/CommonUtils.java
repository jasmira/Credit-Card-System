package com.assignment.creditcardsystem.util;

import com.assignment.creditcardsystem.entity.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public static List<CreditCard> createTestCreditCardList() {
        List<CreditCard> cards = new ArrayList<>();
        CreditCard card1 = setCreditCard(1, "Bob", 123456789007L, 123, 1400);
        CreditCard card2 = setCreditCard(2, "Alice", 6590162738L, 456, 900);
        cards.add(card1);
        cards.add(card2);
        return cards;
    }

    private static CreditCard setCreditCard(long id, String name, long cardNumber, long limits, double balance) {
        CreditCard card = new CreditCard();
        card.setId(id);
        card.setName(name);
        card.setCardNumber(cardNumber);
        card.setLimits(limits);
        card.setBalance(balance);
        return card;
    }

    public static CreditCard createTestCreditCard() {
        CreditCard card = setCreditCard(3, "Jane", 1052118567L, 789, 4500);
        return card;
    }

    public static CreditCard createTestInvalidCreditCard() {
        CreditCard card = setCreditCard(4, "Caren", 12, 100, 2500);
        return card;
    }

}
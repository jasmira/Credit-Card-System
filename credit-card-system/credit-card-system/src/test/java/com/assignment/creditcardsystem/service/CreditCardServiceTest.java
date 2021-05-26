package com.assignment.creditcardsystem.service;

import com.assignment.creditcardsystem.entity.CreditCard;
import com.assignment.creditcardsystem.exception.CreditCardInvalidException;
import com.assignment.creditcardsystem.repository.CardRepository;
import com.assignment.creditcardsystem.util.CommonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@SpringBootTest
public class CreditCardServiceTest {

    @Autowired
    private CardService cardService;

    @MockBean
    private CardRepository cardRepository;
    
    @Test
    @DisplayName("Get All credit cards service test")
    public void getAllCardsServiceTest() {
        List<CreditCard> cards = CommonUtils.createTestCreditCardList();
        doReturn(cards).when(cardRepository).findAll();

        //test
        List<CreditCard> cardList = cardService.getAllCards();

        Assertions.assertEquals(2, cardList.size(), "getAllCards() should return 2 cards");
        verify(cardRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Save a new credit card service test")
    public void saveCardServiceTest() throws CreditCardInvalidException {
        CreditCard card = CommonUtils.createTestCreditCard();
        doReturn(card).when(cardRepository).save(card);

        //test
        cardService.saveCardDetails(card);

        verify(cardRepository, times(1)).save(card);
    }

    @Test
    @DisplayName("Test valid credit card number")
    public void checkLuhnServiceTest_WithValidCreditCardNumber() {
        CreditCard card = CommonUtils.createTestCreditCard();

        boolean isValidLuhnNumber = cardService.checkLuhnNumber(card.getCardNumber());
        Assertions.assertTrue(isValidLuhnNumber);
    }

    @Test
    @DisplayName("Test invalid credit card number")
    public void checkLuhnServiceTest_WithInvalidCreditCardNumber() {
        CreditCard card = CommonUtils.createTestInvalidCreditCard();

        boolean isValidLuhnNumber = cardService.checkLuhnNumber(card.getCardNumber());
        Assertions.assertFalse(isValidLuhnNumber);
    }
}

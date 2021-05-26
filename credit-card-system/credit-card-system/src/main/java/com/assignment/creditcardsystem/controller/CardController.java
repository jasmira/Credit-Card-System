package com.assignment.creditcardsystem.controller;

import com.assignment.creditcardsystem.entity.CreditCard;
import com.assignment.creditcardsystem.exception.CreditCardInvalidException;
import com.assignment.creditcardsystem.service.CardService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/cards"})
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    private List<CreditCard> getAllCards() {
        return cardService.getAllCards();
    }

    @PostMapping
    private long addUser(@RequestBody CreditCard card) throws CreditCardInvalidException {
        cardService.saveCardDetails(card);
        return card.getId();
    }
}

package com.assignment.creditcardsystem.controller;

import com.assignment.creditcardsystem.entity.CreditCard;
import com.assignment.creditcardsystem.service.CardService;
import com.assignment.creditcardsystem.util.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CardController.class)
public class CardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("GET API integration test")
    public void testGetAllCards() throws Exception {
        List<CreditCard> cards = CommonUtils.createTestCreditCardList();
        Mockito.when(cardService.getAllCards()).thenReturn(cards);
        mockMvc.perform(get("/cards")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("Bob")))
                .andExpect(jsonPath("$[1].name", Matchers.equalTo("Alice")));
    }

    @Test
    @DisplayName("POST API integration test")
    public void testSaveCard() throws Exception {
        CreditCard card = CommonUtils.createTestCreditCard();
        cardService.saveCardDetails(card);
        String cardJson = mapper.writeValueAsString(card);
        mockMvc.perform(post("/cards").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(cardJson).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}

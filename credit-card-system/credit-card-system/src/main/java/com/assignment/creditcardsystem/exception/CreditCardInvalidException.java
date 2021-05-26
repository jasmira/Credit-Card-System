package com.assignment.creditcardsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Invalid credit card Number. Please enter a valid one.")
public class CreditCardInvalidException extends Exception {

    private static final long serialVersionUID = 1L;
}

package com.johnny.checkout.controller;

import com.johnny.checkout.exception.GeneralException;
import com.johnny.checkout.request.CheckoutRequest;
import com.johnny.checkout.response.CheckoutResponse;
import com.johnny.checkout.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }


    @PostMapping("/proceed")
    public ResponseEntity<Object> proceed(@RequestBody CheckoutRequest request) {

        try {
            checkoutService.checkCardNumber(request);
            checkoutService.checkExpiryDate(request);
            checkoutService.checkCvv(request);

            return new ResponseEntity<>(new CheckoutResponse("success","Your card details are correct"), HttpStatus.OK);

        } catch (GeneralException e) {
            return new ResponseEntity<>(new CheckoutResponse("error", e.getCardNumberError() == null ? (e.getExpiryDateError() == null ? e.getCvvError() : e.getExpiryDateError()) : e.getCardNumberError()), HttpStatus.BAD_REQUEST);
        }
    }
}

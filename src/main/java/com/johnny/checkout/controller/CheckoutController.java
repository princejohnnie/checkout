package com.johnny.checkout.controller;

import com.johnny.checkout.request.CheckoutRequest;
import com.johnny.checkout.response.CheckoutResponse;
import com.johnny.checkout.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/")
    public String checkout() {
        return "checkout";
    }


    @PostMapping("/validate-card")
    public ResponseEntity<CheckoutResponse> proceed(@RequestBody CheckoutRequest request) {

        return checkoutService.checkCardDetails(request);

    }
}

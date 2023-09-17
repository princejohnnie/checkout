package com.johnny.checkout.response;

public class CheckoutResponse {
    public String status;
    public String message;
    public CheckoutResponse(String status, String message) {
        this.status =  status;
        this.message = message;
    }
}

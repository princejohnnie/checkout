package com.johnny.checkout.request;

public class CheckoutRequest {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public CheckoutRequest(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }
}

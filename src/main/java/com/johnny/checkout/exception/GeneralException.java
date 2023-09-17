package com.johnny.checkout.exception;

public class GeneralException extends Exception {
    private final String cardNumberError;
    private final String expiryDateError;
    private final String cvvError;

    public GeneralException(String cardNumberError, String expiryDateError, String cvvError) {
        this.cardNumberError = cardNumberError;
        this.expiryDateError = expiryDateError;
        this.cvvError = cvvError;
    }

    public String getCardNumberError() {
        return cardNumberError;
    }

    public String getExpiryDateError() {
        return expiryDateError;
    }

    public String getCvvError() {
        return cvvError;
    }
}

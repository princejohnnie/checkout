package com.johnny.checkout.response;

import org.springframework.lang.Nullable;

import java.util.HashMap;

public class CheckoutResponse {
    private String status;
    private String message;
    private HashMap<String, String> errors;

    public CheckoutResponse(String status, String message, @Nullable HashMap<String, String> errors) {
        this.status =  status;
        this.message = message;
        this.errors = errors;
    }
    public String getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }
}

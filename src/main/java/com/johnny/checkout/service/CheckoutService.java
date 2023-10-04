package com.johnny.checkout.service;

import com.johnny.checkout.request.CheckoutRequest;
import com.johnny.checkout.response.CheckoutResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class CheckoutService {


    public ResponseEntity<CheckoutResponse> checkCardDetails(CheckoutRequest checkoutRequest) {

        String cardNumberResponse = checkCardNumber(checkoutRequest.getCardNumber().trim());
        boolean invalidExpiryDate = checkExpiryDate(checkoutRequest.getExpiryDate().trim());
        String cvvResponse = checkCvv(checkoutRequest.getCardNumber().trim(), checkoutRequest.getCvv().trim());


        if (cardNumberResponse.isEmpty() && !invalidExpiryDate && cvvResponse.isEmpty()) {
            return new ResponseEntity<>(
                    new CheckoutResponse("success", "Your Card details are correct", null),
                    HttpStatus.OK);
        }

        HashMap<String, String> errorMap = new LinkedHashMap<>();
        if (!cardNumberResponse.isEmpty()) {
            errorMap.put("cardNumber", cardNumberResponse);
        }
        if (invalidExpiryDate) {
            errorMap.put("expiryDate", "Invalid Expiry Date");
        }
        if (!cvvResponse.isEmpty()) {
            errorMap.put("cvv", cvvResponse);
        }

        return new ResponseEntity<>(
                new CheckoutResponse("error", "Validation error", errorMap),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private String checkCardNumber(String cardNumber) {
        if (cardNumber.length() >= 16 && cardNumber.length() <= 19) {
            if (!checkLuhn(cardNumber))
                return "Incorrect Card Number";
        } else {
            return "Card number should be between 16 and 19 digits";
        }
        return "";
    }

    private boolean checkExpiryDate(String expiryDate) {
        String month = expiryDate.split("/")[0];
        String year = expiryDate.split("/")[1];

        int inputMonthValue = Integer.parseInt(month);
        int inputYearValue = Integer.parseInt(year);

        long currentTime = System.currentTimeMillis();

        DateFormat formatter = new SimpleDateFormat("MM yy");
        Date date = new Date(currentTime);

        String currentDate = formatter.format(date);

        String currentMonth = currentDate.split(" ")[0];
        String currentYear = currentDate.split(" ")[1];

        int currentMonthValue = Integer.parseInt(currentMonth);
        int currentYearValue = Integer.parseInt(currentYear);

        if (inputMonthValue < 1 || inputMonthValue > 12) {
            return true;
        }

        if (inputYearValue < currentYearValue) {
            return true;
        } else if (inputYearValue == currentYearValue) {
            return inputMonthValue <= currentMonthValue;
        }

        return false;
    }

    private String checkCvv(String cardNumber, String cvv) {
        if (cardNumber.startsWith("34") || cardNumber.startsWith("37")) {
            if (cvv.length() != 4) {
                return "CVV must be 4 digits for your Card type";
            }
        } else {
            if (cvv.length() != 3) {
                return "CVV must be 3 digits";
            }
        }
        return "";
    }

    private boolean checkLuhn(String cardNo) {
        int length = cardNo.length();

        int sum = 0;
        boolean isSecond = false;

        for (int i = length - 1; i >= 0; i--) {

            int currentDigit = Character.getNumericValue(cardNo.charAt(i));

            // Multiply digits at even position indexes by 2
            if (isSecond)
                currentDigit = currentDigit * 2;

            // Use normal and modulo division to handle cases (i.e even position indexes) that make two digits after multiplying by 2
            sum += currentDigit / 10;
            sum += currentDigit % 10;

            isSecond = !isSecond;
        }

        return (sum % 10 == 0); // Modulo 10 division of total sum
    }
}

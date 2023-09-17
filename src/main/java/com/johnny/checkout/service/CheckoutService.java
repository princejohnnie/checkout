package com.johnny.checkout.service;

import com.johnny.checkout.exception.GeneralException;
import com.johnny.checkout.request.CheckoutRequest;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CheckoutService {
    public void checkExpiryDate(CheckoutRequest checkoutRequest) throws GeneralException {
        String expiryDate = checkoutRequest.getExpiryDate();
        String month = expiryDate.split("/")[0];
        String year = expiryDate.split("/")[1];

//        System.out.println("Inserted Date -> " + month + " " + year);

        int inputMonthValue = Integer.parseInt(month);
        int inputYearValue = Integer.parseInt(year);

        long currentTime = System.currentTimeMillis();

        DateFormat formatter = new SimpleDateFormat("MM yy");
        Date date = new Date(currentTime);

        String currentDate = formatter.format(date);

        String currentMonth = currentDate.split(" ")[0];
        String currentYear = currentDate.split(" ")[1];

//        System.out.println("Current Date -> " + currentMonth + " " + currentYear);

        int currentMonthValue = Integer.parseInt(currentMonth);
        int currentYearValue = Integer.parseInt(currentYear);

        if (inputYearValue < currentYearValue) {
            throw new GeneralException(null, "Invalid Expiry Date", null);
        } else if (inputYearValue == currentYearValue) {
            if (inputMonthValue <= currentMonthValue) {
                throw new GeneralException(null, "Invalid Expiry Date", null);
            }
        }
    }

    public void checkCvv(CheckoutRequest request) throws GeneralException {
        String cvv = request.getCvv();
        String cardNumber = request.getCardNumber();

        if (cardNumber.startsWith("34") || cardNumber.startsWith("37")) {
            if (cvv.length() != 4) {
                throw new GeneralException(null, null, "Invalid CVV");
            }
        } else {
            if (cvv.length() != 3) {
                throw new GeneralException(null, null, "Invalid CVV");
            }
        }
    }

    public void checkCardNumber(CheckoutRequest request) throws GeneralException {
        String cardNumber = request.getCardNumber();

        if (cardNumber.length() >= 16 && cardNumber.length() <= 19) {
            if (checkLuhn(cardNumber)) {
                System.out.println("Card Number is correct");
            } else {
                throw new GeneralException("Incorrect Card Number", null, null);
            }
        } else {
            throw new GeneralException("Invalid Card Number", null, null);
        }
    }

    private boolean checkLuhn(String cardNo) {
        String cardNum = "79927398713";
        int length = cardNo.length();

        int sum = 0;
        boolean isSecond = false;

        for (int i = length - 1; i >= 0; i--) {

            int d = cardNo.charAt(0)  - '0';
            int currentDigit = Character.getNumericValue(cardNo.charAt(i));
//            System.out.println("Current digit -> " + currentDigit);

            // Multiply digits at even position indexes by 2
            if (isSecond)
                currentDigit = currentDigit * 2;

            // Use normal and modulo division to handle cases (even position indexes) that make two digits after multiplying by 2
            sum += currentDigit / 10;
            sum += currentDigit % 10;
//            System.out.println("Current Sum -> " + sum);

//            System.out.println("Second place -> " + isSecond);
            isSecond = !isSecond;
        }
        return (sum % 10 == 0); // Modulo 10 division of total sum
    }
}

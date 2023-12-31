package com.johnny.checkout.service;

import com.johnny.checkout.request.CheckoutRequest;
import com.johnny.checkout.response.CheckoutResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceTest {

    @InjectMocks
    private CheckoutService checkoutService;


    @Test
    public void CheckoutService_CheckCardDetails_ReturnsValidCardNumber() {
        CheckoutRequest checkoutRequest = new CheckoutRequest("4012888888881881", "12/23", "123");

        ResponseEntity<CheckoutResponse> checkoutResponse = checkoutService.checkCardDetails(checkoutRequest);

        Assertions.assertThat(checkoutResponse).isNotNull();
        Assertions.assertThat(checkoutResponse.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    public void CheckoutService_CheckCardDetails_ReturnsInvalidCardNumber() {
        CheckoutRequest checkoutRequest = new CheckoutRequest("401288888888188", "12/23", "123");

        ResponseEntity<CheckoutResponse> checkoutResponse = checkoutService.checkCardDetails(checkoutRequest);

        Assertions.assertThat(checkoutResponse).isNotNull();
        Assertions.assertThat(checkoutResponse.getStatusCode().value()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
        Assertions.assertThat(checkoutResponse.getBody().getErrors().get("cardNumber")).isEqualTo("Card number should be between 16 and 19 digits");

    }

    @Test
    public void CheckoutService_CheckCardDetails_ReturnsIncorrectCardNumber() {
        CheckoutRequest checkoutRequest = new CheckoutRequest("401288888888188112", "12/23", "123");

        ResponseEntity<CheckoutResponse> checkoutResponse = checkoutService.checkCardDetails(checkoutRequest);

        Assertions.assertThat(checkoutResponse).isNotNull();
        Assertions.assertThat(checkoutResponse.getStatusCode().value()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
        Assertions.assertThat(checkoutResponse.getBody().getErrors().get("cardNumber")).isEqualTo("Incorrect Card Number");

    }

    @Test
    public void CheckoutService_CheckCardDetails_ReturnsInvalidExpiryDate() {
        CheckoutRequest checkoutRequest = new CheckoutRequest("4012888888881881", "12/22", "123");

        ResponseEntity<CheckoutResponse> checkoutResponse = checkoutService.checkCardDetails(checkoutRequest);

        Assertions.assertThat(checkoutResponse).isNotNull();
        Assertions.assertThat(checkoutResponse.getStatusCode().value()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
        Assertions.assertThat(checkoutResponse.getBody().getErrors().get("expiryDate")).isEqualTo("Invalid Expiry Date");

    }

    @Test
    public void CheckoutService_CheckCardDetails_ReturnsInvalidCvv() {
        CheckoutRequest checkoutRequest = new CheckoutRequest("401288888888188", "12/23", "1234");

        ResponseEntity<CheckoutResponse> checkoutResponse = checkoutService.checkCardDetails(checkoutRequest);

        Assertions.assertThat(checkoutResponse).isNotNull();
        Assertions.assertThat(checkoutResponse.getStatusCode().value()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
        Assertions.assertThat(checkoutResponse.getBody().getErrors().get("cvv")).isEqualTo("CVV must be 3 digits");

    }

}

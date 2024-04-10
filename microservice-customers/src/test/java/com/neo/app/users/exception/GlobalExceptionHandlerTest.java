package com.neo.app.users.exception;


import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class GlobalExceptionHandlerTest {

    /*@Test
    public void testHandleResourceNotFoundException() {
        // Arrange
        CustomerNotFoundException ex = new CustomerNotFoundException("Customer not found");
        WebRequest request = mock(WebRequest.class);
        GlobalExceptionHandlerTest handler = new GlobalExceptionHandlerTest();

        // Act
        ResponseEntity<ErrorExceptionResponse> response = handler.testHandleResourceNotFoundException(ex, request);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorExceptionResponse errorResponse = response.getBody();
        assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getStatusCode());
        assertEquals(List.of("Customer not found"), errorResponse.getErrors());
        // Add additional assertions for the formatted time if needed
    }*/
}
package com.example.profilemicroservice.exception;


public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }
}
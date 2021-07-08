package com.epam.flightscontroltool.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorMessage {

    private final int statusCode;

    @NonNull
    private final Date timestamp;

    @NonNull
    private final String message;

    @NonNull
    private final String description;
}

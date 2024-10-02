package com.nameslowly.coinauctions.common.exception;

public record InvalidInputRes(
    String field,
    String message
) {

}
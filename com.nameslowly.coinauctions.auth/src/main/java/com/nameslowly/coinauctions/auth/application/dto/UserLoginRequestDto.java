package com.nameslowly.coinauctions.auth.application.dto;

public record UserLoginRequestDto (
    String username,
    String password
){

}
